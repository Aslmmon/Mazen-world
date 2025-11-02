package com.aslmmovic.mazenworld.domain.useCase


import com.aslmmovic.mazenworld.domain.CategoryItem
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository

/**
 * A use case to publish a list of categories to the repository.
 */
class PublishCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(categories: List<CategoryItem>) {
        categoryRepository.publishCategories(categories)
    }
}
