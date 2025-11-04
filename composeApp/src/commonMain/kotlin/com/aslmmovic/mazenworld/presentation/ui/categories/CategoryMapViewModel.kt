package com.aslmmovic.mazenworld.presentation.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.useCase.GetCategoriesUseCase
import com.aslmmovic.mazenworld.domain.useCase.PublishCategoriesUseCase
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import com.aslmmovic.mazenworld.utils.loadingBetweenScreensDelay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CategoryMapViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val publishCategoryUseCase: PublishCategoriesUseCase

) : ViewModel() {

    private val _state = MutableStateFlow<CategoryState>(CategoryState.Loading)
    val state: StateFlow<CategoryState> = _state


    init {
        loadCategories()
    }


    private fun loadCategories() {
        viewModelScope.launch {
            delay(loadingBetweenScreensDelay)
            getCategoriesUseCase().collect { result ->
                when (result) {
                    is AppResult.Success -> {
                        _state.value = CategoryState.Success(result.data)
                    }

                    is AppResult.Failure -> {
                        _state.value = CategoryState.Error(result.error)
                    }
                }
            }
        }
    }


}

sealed class CategoryState {
    data object Loading : CategoryState()
    data class Success(val categories: List<CategoryDto>) : CategoryState()
    data class Error(val error: AppError) : CategoryState()
}

