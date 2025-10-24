package com.aslmmovic.mazenworld.data.source

// commonMain/data/MockState.kt

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// This object simulates a simple in-memory database
object MockState {
    private val _stars = MutableStateFlow(16)
    val stars: StateFlow<Int> = _stars

    private val _musicEnabled = MutableStateFlow(true)
    val musicEnabled: StateFlow<Boolean> = _musicEnabled

    private val _soundEnabled = MutableStateFlow(true)
    val soundEnabled: StateFlow<Boolean> = _soundEnabled

    // Function to manually update the MOCK state
    fun updateMusic(enabled: Boolean) {
        _musicEnabled.value = enabled
    }

    fun updateSound(enabled: Boolean) {
        _soundEnabled.value = enabled
    }

    fun updateStars(count: Int) {
        _stars.value = count
    }
}