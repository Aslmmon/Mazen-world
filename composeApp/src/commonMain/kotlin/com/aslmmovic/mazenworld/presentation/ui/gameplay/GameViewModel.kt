package com.aslmmovic.mazenworld.presentation.ui.gameplay

// commonMain/presentation/viewmodel/GameViewModel.kt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.data.source.getMockQuestionsForCategory
import com.aslmmovic.mazenworld.domain.GameQuestion
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val categoryId: String,
    private val gameRepository: GameRepository // To update stars (mocked)
) : ViewModel() {

    // List of all questions for the current level
    private lateinit var questions: List<GameQuestion>

    private val _state = MutableStateFlow(GameState(currentQuestion = null))
    val state: StateFlow<GameState> = _state

    init {
        loadGameContent()
    }

    private fun loadGameContent() {
        // MOCK LOAD: In a real app, this would be a suspend function in the Repository.
        questions = getMockQuestionsForCategory(categoryId)

        _state.update {
            it.copy(
                currentQuestion = questions.firstOrNull(),
                totalQuestions = questions.size,
                isLoading = false
            )
        }
    }

    fun processAnswer(selectedOptionId: String) {
        // Only process if the game isn't complete
        if (_state.value.isLevelComplete) return

        val currentQ = _state.value.currentQuestion ?: return

        if (selectedOptionId == currentQ.correctAnswerId) {
            // Correct Answer Logic
            _state.update {
                it.copy(
                    feedbackMessage = "صحيح! ممتاز!",
                    currentQuestionIndex = it.currentQuestionIndex + 1
                )
            }

            // Advance to the next question
            val nextIndex = _state.value.currentQuestionIndex
            if (nextIndex < questions.size) {
                _state.update {
                    it.copy(
                        currentQuestion = questions[nextIndex],
                        feedbackMessage = null
                    )
                }
            } else {
                // Level Complete Logic
                _state.update {
                    it.copy(
                        isLevelComplete = true,
                        feedbackMessage = "أنهيت المستوى! أحسنت!"
                    )
                }
                awardStarsAndSaveProgress()
            }

        } else {
            // Incorrect Answer Logic
            _state.update { it.copy(feedbackMessage = "حاول مرة أخرى.") }
            //AudioPlayerManager.playSound("incorrect_buzz") // Use your audio manager for feedback
        }
    }

    private fun awardStarsAndSaveProgress() {
        viewModelScope.launch {
            // MOCK REWARD: Award a fixed number of stars
            //  gameRepository.addStars(25) // Assuming you add this mock function to your repository
        }
    }
}

data class GameState(
    val currentQuestion: GameQuestion?,
    val totalQuestions: Int = 5,
    val currentQuestionIndex: Int = 0,
    val isLoading: Boolean = true,
    val feedbackMessage: String? = null,
    val isLevelComplete: Boolean = false
)