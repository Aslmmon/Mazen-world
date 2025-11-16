package com.aslmmovic.mazenworld.presentation.ui.gameplay

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.data.model.GameOptionDto
import com.aslmmovic.mazenworld.data.model.GameQuestionDto
import com.aslmmovic.mazenworld.domain.useCase.game_play.GetQuestionsUseCase
import com.aslmmovic.mazenworld.domain.useCase.game_play.PublishQuestionsUseCase
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import com.aslmmovic.mazenworld.utils.loadingBetweenScreensDelay
import com.aslmmovic.mazenworld.utils.provideAudioPlayerManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.allDrawableResources

class GameViewModel(
    private val categoryId: String,
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val publishQuestionsUseCase: PublishQuestionsUseCase // Keep for potential use
) : ViewModel() {

    private val _state =
        MutableStateFlow<GameState>(GameState.Loading) // The initial state is now self-contained
    val state: StateFlow<GameState> = _state


    private val player = provideAudioPlayerManager()

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
                                currentQuestionIndex = 0
                            )
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
        val currentState = _state.value
        // Only process answers if we are in the Success state
        if (currentState !is GameState.Success) return

        val correctAnswerId = currentState.currentQuestion.correctAnswerId

        if (selectedOptionId == correctAnswerId) {

            playCorrectAnswerSound()

            _state.update {
                (it as GameState.Success).copy(feedbackMessage = "Correct!", score = it.score + 1)
            }

            viewModelScope.launch {
                delay(800)
                val nextIndex = currentState.currentQuestionIndex + 1
                Log.e("test", "processAnswer: $nextIndex")
                if (nextIndex < currentState.questions.size) {
                    // Go to the next question
                    _state.value = currentState.copy(
                        currentQuestionIndex = nextIndex,
                        feedbackMessage = null
                    )
                } else {
                    // Level Complete
                    _state.value = currentState.copy(isLevelComplete = true)
                }
            }
        } else {
            playIncorrectAnswerSound()

            _state.update {
                (it as GameState.Success).copy(feedbackMessage = "Try again.")
            }
        }
    }

    private fun playCorrectAnswerSound() {
        viewModelScope.launch {
            player.playSoundEffect(Res.readBytes("files/correct_chime.mp3"))
        }
    }

    private fun playIncorrectAnswerSound() {
        viewModelScope.launch {
            player.playSoundEffect(Res.readBytes("files/error_chime.mp3"))
        }
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
