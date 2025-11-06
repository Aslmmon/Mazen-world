package com.aslmmovic.mazenworld.domain.useCase.game_play

import com.aslmmovic.mazenworld.data.model.GameQuestionDto
import com.aslmmovic.mazenworld.domain.respository.GamePlayRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import kotlinx.coroutines.flow.Flow

class GetQuestionsUseCase(private val repository: GamePlayRepository) {

    /**
     * Invokes the use case to fetch questions for a specific category.
     *
     * @param categoryId The ID of the category.
     * @return A Flow emitting the result of the operation.
     */
    operator fun invoke(categoryId: String): Flow<AppResult<List<GameQuestionDto>, AppError>> {
        return repository.getQuestionsForCategory(categoryId)
    }
}
