package com.aslmmovic.mazenworld.presentation.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.MapState
import com.aslmmovic.mazenworld.domain.useCase.GetCategoriesUseCase
import com.aslmmovic.mazenworld.domain.useCase.PublishCategoriesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CategoryMapViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val publishCategoryUseCase: PublishCategoriesUseCase

) : ViewModel() {

    private val _mapState = MutableStateFlow(MapState(categories = emptyList()))
    val mapState: StateFlow<MapState> = _mapState

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    init {
        loadCategories()
    }


    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase()
                .catch { exception ->
                    _message.emit("Failed to load categories: ${exception.message}")
                }
                .collect { categories ->
                    _mapState.value = MapState(categories = categories)
                }
        }
    }


    fun publishCategories() {
        viewModelScope.launch {
            try {
                val currentCategories = emptyList<CategoryDto>()
                if (currentCategories.isEmpty()) {
                    _message.emit("No categories to publish.")
                    return@launch
                }
                publishCategoryUseCase(currentCategories)
                _message.emit("Categories published successfully!")
            } catch (e: Exception) {
                e.printStackTrace()
                _message.emit("Failed to publish categories: ${e.message}")
            }
        }
    }

}

