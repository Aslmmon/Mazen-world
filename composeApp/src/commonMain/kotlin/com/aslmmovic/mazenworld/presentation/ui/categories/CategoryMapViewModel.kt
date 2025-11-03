package com.aslmmovic.mazenworld.presentation.ui.categories

import androidx.compose.ui.semantics.error
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.MapState
import com.aslmmovic.mazenworld.domain.useCase.GetCategoriesUseCase
import com.aslmmovic.mazenworld.domain.useCase.PublishCategoriesUseCase
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import com.aslmmovic.mazenworld.domain.util.toUserFriendlyMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
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
            getCategoriesUseCase().collect { result ->
                when (result) {
                    is AppResult.Success -> {
                        _mapState.value = MapState(categories = result.data)
                    }

                    is AppResult.Failure -> {
                        _message.emit(result.error.toUserFriendlyMessage())
                    }
                }
            }
        }
    }


    fun publishCategories() {
        viewModelScope.launch {
            val currentCategories = emptyList<CategoryDto>() // Using an empty list for the example
            if (currentCategories.isEmpty()) {
                _message.emit("No categories to publish.")
                return@launch
            }

            when (val result = publishCategoryUseCase(currentCategories)) {
                is AppResult.Success -> {
                    _message.emit("Categories published successfully!")
                }

                is AppResult.Failure -> {
                    _message.emit(result.error.toUserFriendlyMessage())
                }
            }
        }
    }

}

