package com.aslmmovic.mazenworld.domain.useCase

import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.CategoryItem
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Flow<List<CategoryDto>> = categoryRepository.getCategories()
}