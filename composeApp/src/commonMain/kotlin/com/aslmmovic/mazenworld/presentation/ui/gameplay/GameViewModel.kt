package com.aslmmovic.mazenworld.presentation.ui.gameplay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.data.model.GameQuestionDto
import com.aslmmovic.mazenworld.domain.useCase.game_play.GetQuestionsUseCase
import com.aslmmovic.mazenworld.domain.useCase.game_play.PublishQuestionsUseCase
import com.aslmmovic.mazenworld.domain.util.AppResult
import com.aslmmovic.mazenworld.utils.loadingBetweenScreensDelay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val categoryId: String,
    private val getQuestionsUseCase: GetQuestionsUseCase,
    private val publishQuestionsUseCase: PublishQuestionsUseCase // Keep for potential use
) : ViewModel() {

    private val _state = MutableStateFlow(GameState(currentQuestion = null))
    val state: StateFlow<GameState> = _state

    private var questions: List<GameQuestionDto> = emptyList()
    private var currentQuestionIndex = 0

    init {
        loadGameContent()
    }

    private fun loadGameContent() {
        viewModelScope.launch {
            delay(loadingBetweenScreensDelay)

            getQuestionsUseCase(categoryId).collect { result ->
                when (result) {
                    is AppResult.Success -> {
                        questions = result.data
                        _state.update {
                            it.copy(
                                currentQuestion = questions.firstOrNull(),
                                totalQuestions = questions.size,
                                isLoading = false
                            )
                        }
                    }
                    is AppResult.Failure -> {
                        _state.update { it.copy(feedbackMessage = "Failed to load questions.", isLoading = false) }
                    }
                }
            }
        }
    }

    fun processAnswer(selectedOptionId: String) {
        if (_state.value.isLevelComplete) return

        val currentQ = _state.value.currentQuestion ?: return

        if (selectedOptionId == currentQ.correctAnswerId) {
            _state.update { it.copy(feedbackMessage = "Correct!", score = it.score + 1) }

            viewModelScope.launch {
                delay(800)
                val nextIndex = currentQuestionIndex + 1
                if (nextIndex < questions.size) {
                    currentQuestionIndex = nextIndex
                    _state.update {
                        it.copy(
                            currentQuestion = questions[nextIndex],
                            feedbackMessage = null
                        )
                    }
                } else {
                    _state.update { it.copy(isLevelComplete = true, feedbackMessage = "Level Complete!") }
                    // awardStarsAndSaveProgress()
                }
            }
        } else {
            _state.update { it.copy(feedbackMessage = "Try again.") }
        }
    }
}

data class GameState(
    val currentQuestion: GameQuestionDto?,
    val totalQuestions: Int = 0,
    val currentQuestionIndex: Int = 0,
    val isLoading: Boolean = true,
    val feedbackMessage: String? = null,
    val isLevelComplete: Boolean = false,
    val score: Int = 0
)
