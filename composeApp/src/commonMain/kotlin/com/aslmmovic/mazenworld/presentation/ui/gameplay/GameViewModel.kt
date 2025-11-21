package com.aslmmovic.mazenworld.presentation.ui.gameplay

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.data.model.GameQuestionDto
import com.aslmmovic.mazenworld.domain.useCase.game_play.AnswerResult
import com.aslmmovic.mazenworld.domain.useCase.game_play.GetQuestionsUseCase
import com.aslmmovic.mazenworld.domain.useCase.game_play.ProcessAnswerUseCase
import com.aslmmovic.mazenworld.domain.useCase.game_play.PublishQuestionsUseCase
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import com.aslmmovic.mazenworld.presentation.ui.settings.GameAudioManager
import com.aslmmovic.mazenworld.utils.loadingBetweenScreensDelay
import com.aslmmovic.mazenworld.utils.loadingBetweenSounds
import com.aslmmovic.mazenworld.utils.loadingTiger
import com.aslmmovic.mazenworld.utils.provideAudioPlayerManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mazenworld.composeapp.generated.resources.Res

class GameViewModel(
    private val categoryId: String,
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val processAnswerUseCase: ProcessAnswerUseCase, // Injected dependency
    private val gameAudioManager: GameAudioManager
) : ViewModel() {

    private val _state =
        MutableStateFlow<GameState>(GameState.Loading) // The initial state is now self-contained
    val GameContentState: StateFlow<GameState> = _state


    // Get the player instance once
    init {
        loadGameContent()
    }

    private fun loadGameContent() {
        viewModelScope.launch {
            getQuestionsUseCase(categoryId).collect { result ->
                delay(loadingBetweenScreensDelay)
                when (result) {
                    is AppResult.Success -> {
                        val loadedQuestions = result.data // Shuffle for variety
                        if (loadedQuestions.isEmpty()) {
                            _state.value = GameState.Error(AppError.Unknown("No questions found"))
                        } else {
                            _state.value = GameState.Success(
                                questions = loadedQuestions,
                            )
                            gameAudioManager.playQuestionVoice(loadedQuestions.first().questionVoice)
                        }
                    }

                    is AppResult.Failure -> {
                        _state.value = GameState.Error(result.error)
                    }
                }
            }
        }
    }

    fun processAnswer(selectedOptionId: String) {

        val currentState = _state.value as? GameState.Success ?: return
        when (val result = processAnswerUseCase(currentState, selectedOptionId)) {

            is AnswerResult.Correct -> {

                viewModelScope.launch {
                    gameAudioManager.playCorrectSound()
                    delay(loadingBetweenSounds)
                    gameAudioManager.playSpecificAnswerSound(result.correctAnswerSound)
                    _state.update {
                        (it as GameState.Success).copy(
                            currentQuestionIndex = it.currentQuestionIndex + 1,
                            feedbackMessage = null
                        )
                    }
                }
            }

            is AnswerResult.Incorrect -> gameAudioManager.playIncorrectSound()


            is AnswerResult.LevelComplete -> {
                viewModelScope.launch {
                    gameAudioManager.playCorrectSound() // Play final correct sound
                    delay(loadingBetweenScreensDelay)
                    gameAudioManager.playLevelCompleteSound()
                    _state.update {
                        (it as GameState.Success).copy(
                            score = result.finalScore,
                            isLevelComplete = true
                        )
                    }
                }

            }


        }

    }

    fun retryLevel() {
        _state.value = GameState.Loading
        loadGameContent()
    }
}


sealed class GameState {
    data object Loading : GameState()
    data class Error(val message: AppError) : GameState()
    data class Success(
        val questions: List<GameQuestionDto>,
        val currentQuestionIndex: Int = 0,
        val score: Int = 0,
        val feedbackMessage: String? = null,
        val isLevelComplete: Boolean = false
    ) : GameState() {
        val currentQuestion: GameQuestionDto get() = questions[currentQuestionIndex]
        val totalQuestions: Int get() = questions.size
    }
}
