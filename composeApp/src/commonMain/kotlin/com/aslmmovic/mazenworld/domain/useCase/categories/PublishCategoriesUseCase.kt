package com.aslmmovic.mazenworld.domain.useCase.categories

import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult

/**
 * A use case to publish a list of categories to the repository.
 */
class PublishCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(categories: List<CategoryDto>) : AppResult<Unit, AppError> {
       return categoryRepository.publishCategories(categories)
    }
}