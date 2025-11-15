package com.aslmmovic.mazenworld.data.repositoryImpl

import com.aslmmovic.mazenworld.data.model.GameOptionDto
import com.aslmmovic.mazenworld.data.model.GameQuestionDto
import com.aslmmovic.mazenworld.domain.respository.GamePlayRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import mazenworld.composeapp.generated.resources.Res

private val json = Json { ignoreUnknownKeys = true } // Flexible JSON parser
private val questionsFilePath = "files/questions.json"

class GamePlayRepositoryImpl : GamePlayRepository {

    override fun getQuestionsForCategory(categoryId: String): Flow<AppResult<List<GameQuestionDto>, AppError>> =
        flow {
            try {
                // 1. Read the entire questions file
                val allQuestions = json.decodeFromString<List<GameQuestionWithRawOptions>>(
                    Res.readBytes(questionsFilePath).decodeToString()
                )

                val filteredQuestions =
                    allQuestions.filter { it.categoryId.toString() == categoryId }
                val parsedQuestions = filteredQuestions.map { rawQuestion ->
                    val optionsList =
                        json.decodeFromString<List<GameOptionDto>>(rawQuestion.options)
                    rawQuestion.toGameQuestionDto(optionsList)
                }

                emit(AppResult.Success(parsedQuestions))

            } catch (e: Exception) {
                emit(AppResult.Failure(AppError.Unknown("Failed to load questions: ${e.message}")))
            }
        }

    /**
     * This is a no-op as the data is from a local file.
     */
    override suspend fun publishQuestions(
        categoryId: String,
        questions: List<GameQuestionDto>
    ): AppResult<Unit, AppError> {
        return AppResult.Success(Unit) // Not applicable for local JSON
    }
}

/**
 * A temporary data class to handle the nested JSON string issue.
 * It reads the `options` field as a files String.
 */
@kotlinx.serialization.Serializable
private data class GameQuestionWithRawOptions(
    val idx: Int,
    val id: String,
    val categoryId: Int,
    val questionText: String,
    val questionImageUrl: String,
    val options: String, // Reads the nested JSON as a single string
    val correctAnswerId: String
) {
    /**
     * Converts this files DTO into the final, clean GameQuestionDto.
     */
    fun toGameQuestionDto(parsedOptions: List<GameOptionDto>): GameQuestionDto {
        return GameQuestionDto(
            categoryId = this.categoryId,
            questionText = this.questionText,
            questionImageUrl = this.questionImageUrl,
            options = parsedOptions,
            correctAnswerId = this.correctAnswerId
        )
    }
}
