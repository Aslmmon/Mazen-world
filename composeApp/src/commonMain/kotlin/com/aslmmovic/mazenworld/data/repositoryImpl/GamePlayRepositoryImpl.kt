package com.aslmmovic.mazenworld.data.repositoryImpl

import com.aslmmovic.mazenworld.data.model.GameQuestionDto
import com.aslmmovic.mazenworld.domain.respository.GamePlayRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.exception.PostgrestRestException
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val categoryIdColumn = "categoryId"
const val QuestionsTableName = "Questions"


class GamePlayRepositoryImpl(private val supabaseClient: SupabaseClient) : GamePlayRepository {

    override fun getQuestionsForCategory(categoryId: String): Flow<AppResult<List<GameQuestionDto>, AppError>> =
        flow {
            try {
                val questions = supabaseClient.from(QuestionsTableName)
                    .select {
                        filter {
                            eq(categoryIdColumn, categoryId.toInt())
                        }
                    }
                    .decodeList<GameQuestionDto>()
                emit(AppResult.Success(questions))
            } catch (e: Exception) {
                emit(AppResult.Failure(AppError.Unknown(e.message)))
            }
        }

    override suspend fun publishQuestions(
        categoryId: String,
        questions: List<GameQuestionDto>
    ): AppResult<Unit, AppError> {
        return try {
            // Add the categoryId to each question before publishing
            val questionsWithCategoryId = questions.map { it.copy(categoryId = categoryId) }
            supabaseClient.from("Questions").insert(questionsWithCategoryId)
            AppResult.Success(Unit)
        } catch (e: PostgrestRestException) {
            AppResult.Failure(AppError.Unknown(e.message))
        } catch (e: Exception) {
            AppResult.Failure(AppError.Unknown(e.message))
        }
    }
}