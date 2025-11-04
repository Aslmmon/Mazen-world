package com.aslmmovic.mazenworld.presentation.ui.gameplay
// commonMain/presentation/viewmodel/GameViewModel.kt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.data.repositoryImpl.MapRepository
import com.aslmmovic.mazenworld.data.source.getMockQuestionsForCategory
import com.aslmmovic.mazenworld.domain.GameQuestion
import com.aslmmovic.mazenworld.utils.loadingBetweenScreensDelay
// ... other imports ...

// Use this for the delay after a correct answer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update // Use update for clean state modification

class GameViewModel(
    private val categoryId: String,
    private val gameRepository: MapRepository // Changed to MapRepository interface
) : ViewModel() {

    private lateinit var questions: List<GameQuestion>
    private val _state = MutableStateFlow(GameState(currentQuestion = null))
    val state: StateFlow<GameState> = _state
    private var currentQuestionIndex = 0

    init {
        loadGameContent()
    }

    private fun loadGameContent() {
        // ... (existing mock load logic) ...
        viewModelScope.launch {
            delay(loadingBetweenScreensDelay)


            questions = getMockQuestionsForCategory(categoryId)

            _state.update {
                it.copy(
                    currentQuestion = questions.firstOrNull(),
                    totalQuestions = questions.size,
                    isLoading = false
                )
            }
        }

    }

    fun processAnswer(selectedOptionId: String) {
        // Prevent multiple submissions or submissions after completion
        if (_state.value.isLevelComplete) return

        val currentQ = _state.value.currentQuestion ?: return

        if (selectedOptionId == currentQ.correctAnswerId) {
            // --- CORRECT ANSWER LOGIC ---

            // 1. Give immediate visual/audio feedback
            _state.update { it.copy(feedbackMessage = "صحيح! ممتاز!", score = it.score + 1) }
            // AudioPlayerManager.playSound("correct_chime")

            // 2. Schedule the next action
            viewModelScope.launch {
                delay(800) // Wait for 800ms for the user to see the feedback

                // 3. Advance logic
                val nextIndex = currentQuestionIndex + 1
                if (nextIndex < questions.size) {
                    // Go to next question
                    currentQuestionIndex = nextIndex
                    _state.update {
                        it.copy(
                            currentQuestion = questions[nextIndex],
                            feedbackMessage = null
                        )
                    }
                } else {
                    // Level Complete
                    _state.update {
                        it.copy(
                            isLevelComplete = true,
                            feedbackMessage = "أنهيت المستوى! أحسنت!"
                        )
                    }
                    awardStarsAndSaveProgress()
                }
            }

        } else {
            // --- INCORRECT ANSWER LOGIC ---
            // 1. Give feedback (UI can disable the incorrect option if needed)
            _state.update { it.copy(feedbackMessage = "حاول مرة أخرى.") }
            //    AudioPlayerManager.playSound("incorrect_buzz")
        }
    }

    private fun awardStarsAndSaveProgress() {
        viewModelScope.launch {
            // MOCK REWARD: Award 5 stars for completing a mock level
            gameRepository.addStars(25)

            // Optional: You could add a slight delay here before navigating back
            // to ensure the user sees the star count update on the screen.
        }
    }
}

data class GameState(
    val currentQuestion: GameQuestion?,
    val totalQuestions: Int = 5,
    val currentQuestionIndex: Int = 0,
    val isLoading: Boolean = true,
    val feedbackMessage: String? = null,
    val isLevelComplete: Boolean = false,
    val score: Int = 0 // Tracks correct answers
)