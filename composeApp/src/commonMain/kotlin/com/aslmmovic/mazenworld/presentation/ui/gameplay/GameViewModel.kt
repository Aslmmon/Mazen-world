package com.aslmmovic.mazenworld.presentation.ui.gameplay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.data.model.GameOptionDto
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
        //  publishDummyQuestions() // Uncomment this line temporarily to publish data
    }

    private fun loadGameContent() {
        viewModelScope.launch {
            delay(loadingBetweenScreensDelay)

            getQuestionsUseCase(categoryId).collect { result ->
                when (result) {
                    is AppResult.Success -> {
                        questions = result.data // Shuffle questions for variety
                        _state.update {
                            it.copy(
                                currentQuestion = questions.firstOrNull(),
                                totalQuestions = questions.size,
                                isLoading = false,
                                currentQuestionIndex = 0
                            )
                        }
                    }

                    is AppResult.Failure -> {
                        _state.update {
                            it.copy(
                                feedbackMessage = "Failed to load questions.",
                                isLoading = false
                            )
                        }
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
                    _state.update {
                        it.copy(
                            isLevelComplete = true,
                            feedbackMessage = "Level Complete!"
                        )
                    }
                    // awardStarsAndSaveProgress()
                }
            }
        } else {
            _state.update { it.copy(feedbackMessage = "Try again.") }
        }
    }

    /**
     * Creates and uploads a set of dummy questions for predefined categories to Firestore.
     * This is a utility function for seeding the database.
     */
    fun publishDummyQuestions() {
        viewModelScope.launch {
            _state.update { it.copy(feedbackMessage = "Publishing...", isLoading = true) }

            val categories = listOf("animals", "vehicles", "shapes", "alphabet")
            var success = true

            for (catId in categories) {
                val dummyQuestions = createDummyQuestionsFor(catId)
                val result = publishQuestionsUseCase(catId, dummyQuestions)
                if (result is AppResult.Failure) {
                    success = false
                    break
                }
            }

            _state.update {
                it.copy(
                    isLoading = false,
                    feedbackMessage = if (success) "Dummy questions published successfully!" else "Failed to publish questions."
                )
            }
        }
    }

    private fun createDummyQuestionsFor(categoryId: String): List<GameQuestionDto> {
        return (1..5).map { index ->
            val options = (1..4).map { optionIndex ->
                GameOptionDto(
                    id = "${categoryId}_q${index}_opt${optionIndex}",
                    text = "Option $optionIndex",
                    iconUrl = "https://picsum.photos/seed/${categoryId}_q${index}_opt${optionIndex}/200" // Unique placeholder image
                )
            }
            GameQuestionDto(
                questionText = "This is question #$index for the '$categoryId' category",
                questionImageUrl = "https://picsum.photos/seed/${categoryId}_q${index}/400", // Unique placeholder image
                options = options.shuffled(), // Shuffle options
                correctAnswerId = options.first().id // The first option is always the correct one before shuffling
            )
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
