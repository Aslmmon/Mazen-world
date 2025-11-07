package com.aslmmovic.mazenworld.domain.useCase.categories

import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Flow<AppResult<List<CategoryDto>, AppError>> =
        categoryRepository.getCategories()
}