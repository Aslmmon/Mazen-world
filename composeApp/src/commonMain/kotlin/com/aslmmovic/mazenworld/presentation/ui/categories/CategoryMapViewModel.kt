package com.aslmmovic.mazenworld.presentation.ui.categories

// commonMain/presentation/viewmodel/CategoryMapViewModel.kt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.domain.CategoryItem
import com.aslmmovic.mazenworld.domain.MapState
import com.aslmmovic.mazenworld.data.repository.MockMapRepository
import com.aslmmovic.mazenworld.data.source.MOCK_CATEGORIES
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoryMapViewModel(
    private val repository: MockMapRepository = MockMapRepository() // Manual instantiation
) : ViewModel() {

    // MutableStateFlow to hold current list of categories and map state
    private val _mapState = MutableStateFlow(MapState(categories = MOCK_CATEGORIES))
    val mapState: StateFlow<MapState> = _mapState

    // State for showing popups
    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    init {
        // CRITICAL: We need a reactive flow that combines the static categories
        // with the user's current star count to determine lock status.
        repository.getCategoriesFlow()
            .combine(repository.getCurrentUserProfile()) { categories, profile ->
                // This block recalculates the lock status whenever stars change
                val updatedCategories = categories.map { item ->
                    item.copy(
                        // Logic: Item is unlocked if starCost is 0, OR user has enough stars
                        isLocked = item.starCost > 0 && item.starCost > profile.stars,
                        isPremiumContent = item.isPremiumContent
                    )
                }
                MapState(categories = updatedCategories)
            }
            .onEach { _mapState.value = it }
            .launchIn(viewModelScope)
    }

    /**
     * Attempts to unlock a category.
     */
    fun attemptUnlock(category: CategoryItem) {
        viewModelScope.launch {
            if (!category.isLocked) return@launch // Already unlocked, navigate!

            val profile = repository.getCurrentUserProfile().first()
            if (profile.stars >= category.starCost) {
                // SUCCESS: Perform mock transaction
                repository.subtractStars(category.starCost)
                repository.unlockCategory(category.id)
                _message.emit("Category unlocked: ${category.title}!")
                // The 'combine' flow in init{} will automatically update lock status
            } else {
                // FAILURE: Not enough stars
                _message.emit("Not enough stars! You need ${category.starCost - profile.stars} more.")
            }
        }
    }
}