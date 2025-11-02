package com.aslmmovic.mazenworld.domain.respository

import com.aslmmovic.mazenworld.domain.CategoryItem
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<CategoryItem>>
    suspend fun publishCategories(categories: List<CategoryItem>)
}