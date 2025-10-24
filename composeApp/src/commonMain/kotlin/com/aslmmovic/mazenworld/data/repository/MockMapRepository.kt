package com.aslmmovic.mazenworld.data.repository

// commonMain/data/repository/MockMapRepository.kt

import com.aslmmovic.mazenworld.domain.CategoryItem
import com.aslmmovic.mazenworld.data.source.MOCK_CATEGORIES // Import the static data
import com.aslmmovic.mazenworld.domain.UserProfile
import com.aslmmovic.mazenworld.data.source.MockState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface MapRepository {
    fun getCategoriesFlow(): Flow<List<CategoryItem>>
    fun getCurrentUserProfile(): Flow<UserProfile> // Synchronous read from MockState
    suspend fun subtractStars(cost: Int)
    suspend fun unlockCategory(id: String)
}

class MockMapRepository : MapRepository {


    override fun getCategoriesFlow(): Flow<List<CategoryItem>> {
        return kotlinx.coroutines.flow.flowOf(MOCK_CATEGORIES)
    }

    override fun getCurrentUserProfile(): Flow<UserProfile> = combine(
        MockState.stars,
        MockState.musicEnabled,
        MockState.soundEnabled
    ) { stars, musicEnabled, soundEnabled ->
        // Create a new UserProfile object whenever any internal state changes
        UserProfile(stars, musicEnabled, soundEnabled)
    }

    // --- Unlock Logic (Mock Implementation) ---
    override suspend fun subtractStars(cost: Int) {
        val currentStars = MockState.stars.value
        if (currentStars >= cost) {
            MockState.updateStars(currentStars - cost)
        }
    }

    override suspend fun unlockCategory(id: String) {
        // In a real app, this would update the persistent list in DataStore.
        // For mock purposes, we do nothing to the MOCK_CATEGORIES static list.
        println("MOCK: Category $id unlocked.")
    }
}