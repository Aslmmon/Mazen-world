package com.aslmmovic.mazenworld.presentation.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.domain.CategoryItem
import com.aslmmovic.mazenworld.domain.useCase.GetCategoriesUseCase
import com.aslmmovic.mazenworld.domain.MapState
import com.aslmmovic.mazenworld.domain.useCase.PublishCategoriesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.category_alfabet
import mazenworld.composeapp.generated.resources.category_animals
import mazenworld.composeapp.generated.resources.category_shape
import mazenworld.composeapp.generated.resources.category_vehicles

class CategoryMapViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val publishCategoryUseCase: PublishCategoriesUseCase

) : ViewModel() {

    private val _mapState = MutableStateFlow(MapState(categories = emptyList()))
    val mapState: StateFlow<MapState> = _mapState

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    init{
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
                val currentCategories = currentCategories
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

val currentCategories = listOf(
    CategoryItem(
        id = "animals",
        title = "الحيوانات",
        iconResource = Res.drawable.category_animals,
        starCost = 0,
        isLocked = false,
        isPremiumContent = false,
    ),
    CategoryItem(
        id = "fruits",
        title = "الفواكه",
        iconResource = Res.drawable.category_alfabet,
        starCost = 25,
        isLocked = true,
        isPremiumContent = false,
    ),
    CategoryItem(
        id = "shapes",
        title = "الأشكال",
        iconResource = Res.drawable.category_shape,
        starCost = 50,
        isLocked = true,
        isPremiumContent = false,
    ),
    CategoryItem(
        id = "vehicles",
        title = "المركبات",
        iconResource = Res.drawable.category_vehicles,
        starCost = 75,
        isLocked = true,
        isPremiumContent = false,
    ),
    CategoryItem(
        id = "letters",
        title = "الحروف",
        iconResource = Res.drawable.category_alfabet,
        starCost = 999,
        isLocked = true,
        isPremiumContent = true,
    )
)
