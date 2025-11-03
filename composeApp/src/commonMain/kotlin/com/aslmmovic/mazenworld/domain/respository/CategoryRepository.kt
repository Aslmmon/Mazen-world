package com.aslmmovic.mazenworld.domain.respository

import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<AppResult<List<CategoryDto>, AppError>>
    suspend fun publishCategories(categories: List<CategoryDto>): AppResult<Unit, AppError>
}