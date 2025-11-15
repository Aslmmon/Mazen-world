package com.aslmmovic.mazenworld.data.repositoryImpl

import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import mazenworld.composeapp.generated.resources.Res


private val json = Json { ignoreUnknownKeys = true } // Create a flexible Json parser
private val categoriesFilePath = "files/categories.json"

class CategoryRepositoryImpl : CategoryRepository {

    override fun getCategories(): Flow<AppResult<List<CategoryDto>, AppError>> = flow {
        try {
            val categories = json.decodeFromString<List<CategoryDto>>(
                Res.readBytes(categoriesFilePath).decodeToString()
            )
            emit(AppResult.Success(categories))
        } catch (e: Exception) {
            emit(AppResult.Failure(AppError.Unknown("Failed to load categories: ${e.message}")))
        }
    }

    /**
     * This function is now a no-op because the data is local.
     */
    override suspend fun publishCategories(categories: List<CategoryDto>): AppResult<Unit, AppError> {
        // Publishing is not applicable for a local JSON file.
        return AppResult.Success(Unit)
    }
}
