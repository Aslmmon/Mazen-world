package com.aslmmovic.mazenworld.data.repositoryImpl

import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val categoriesTableName = "Categories"

class CategoryRepositoryImpl(private val supabaseClient: SupabaseClient) : CategoryRepository {

    override fun getCategories(): Flow<AppResult<List<CategoryDto>, AppError>> = flow {
        try {
            val categories =
                supabaseClient.from(categoriesTableName).select().decodeList<CategoryDto>()
            emit(AppResult.Success(categories))
        } catch (e: Exception) {
            emit(AppResult.Failure(AppError.Unknown(e.message)))
        }
    }

    override suspend fun publishCategories(categories: List<CategoryDto>): AppResult<Unit, AppError> {
        return try {
            supabaseClient.from(categoriesTableName).insert(categories)
            AppResult.Success(Unit)
        } catch (e: Exception) {
            AppResult.Failure(AppError.Unknown(e.message))
        }
    }
}
