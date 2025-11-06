package com.aslmmovic.mazenworld.domain.respository

import com.aslmmovic.mazenworld.data.model.GameQuestionDto
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import kotlinx.coroutines.flow.Flow

interface GamePlayRepository {


    fun getQuestionsForCategory(categoryId: String): Flow<AppResult<List<GameQuestionDto>, AppError>>

    suspend fun publishQuestions(
        categoryId: String,
        questions: List<GameQuestionDto>
    ): AppResult<Unit, AppError>
}
