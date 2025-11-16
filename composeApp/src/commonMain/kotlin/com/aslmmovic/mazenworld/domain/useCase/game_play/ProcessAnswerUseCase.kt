package com.aslmmovic.mazenworld.domain.useCase.game_play


import com.aslmmovic.mazenworld.presentation.ui.gameplay.GameState

// A result class to hold the outcome of processing an answer
sealed class AnswerResult {
    data class Correct(val newScore: Int, val correctAnswerSound: String?) : AnswerResult()
    data object Incorrect : AnswerResult()
    data class LevelComplete(val finalScore: Int) : AnswerResult()
}

class ProcessAnswerUseCase {

    operator fun invoke(
        currentState: GameState.Success,
        selectedOptionId: String
    ): AnswerResult {
        val isCorrect = selectedOptionId == currentState.currentQuestion.correctAnswerId

        return if (isCorrect) {
            val newScore = currentState.score + 1
            val isLevelNowComplete =
                (currentState.currentQuestionIndex + 1) >= currentState.questions.size

            if (isLevelNowComplete) {
                AnswerResult.LevelComplete(finalScore = newScore)
            } else {
                AnswerResult.Correct(
                    newScore = newScore,
                    correctAnswerSound = currentState.currentQuestion.correctAnswerSound
                )
            }
        } else {
            AnswerResult.Incorrect
        }
    }
}
