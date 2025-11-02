package com.aslmmovic.mazenworld.presentation.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.domain.useCase.GetCategoriesUseCase
import com.aslmmovic.mazenworld.domain.MapState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoryMapViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase
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
                    val updatedCategories = categories.map { it.copy(isLocked = false) }
                    _mapState.value = MapState(categories = updatedCategories)
                }
        }
    }
}
