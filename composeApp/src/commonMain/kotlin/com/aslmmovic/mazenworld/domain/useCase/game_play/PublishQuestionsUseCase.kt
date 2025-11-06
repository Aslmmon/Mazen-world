package com.aslmmovic.mazenworld.domain.useCase.game_play

import com.aslmmovic.mazenworld.data.model.GameQuestionDto
import com.aslmmovic.mazenworld.domain.respository.GamePlayRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult

class PublishQuestionsUseCase(private val repository: GamePlayRepository) {

    /**
     * Invokes the use case to publish questions for a specific category.
     *
     * @param categoryId The ID of the category.
     * @param questions The list of questions to publish.
     * @return An AppResult indicating success or failure.
     */
    suspend operator fun invoke(
        categoryId: String,
        questions: List<GameQuestionDto>
    ): AppResult<Unit, AppError> {
        return repository.publishQuestions(categoryId, questions)
    }
}
