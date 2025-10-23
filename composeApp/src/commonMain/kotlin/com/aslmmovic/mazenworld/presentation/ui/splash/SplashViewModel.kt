package com.aslmmovic.mazenworld.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// presentation/viewmodel/SplashViewModel.kt

class SplashViewModel() : ViewModel() {
    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    init {
        viewModelScope.launch {
            // 1. Simulate a minimum splash display time for better UX
            val minDelay = launch { delay(1000) }
            // 2. Load the persistent data (which hits UserLocalDataSource)
//            gameRepository.getUserProfile()
            // 3. Wait for the minimum delay to finish
            minDelay.join()

            // 4. Mark as loaded
            _isDataLoaded.value = true
        }
    }
}