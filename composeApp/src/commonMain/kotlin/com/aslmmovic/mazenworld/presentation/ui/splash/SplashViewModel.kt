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


    private val _isLoaded = MutableStateFlow(false)
    val isLoaded: StateFlow<Boolean> = _isLoaded

    // State to hold the current loading message
    private val _loadingText = MutableStateFlow("Initializing...")
    val loadingText: StateFlow<String> = _loadingText

    // 🌟 NEW: Progress value (Float from 0.0f to 1.0f)
    private val _progress = MutableStateFlow(0.0f)
    val progress: StateFlow<Float> = _progress


    init {
        viewModelScope.launch {
            val minDelay = launch { delay(1000) }
            minDelay.join()
            _isDataLoaded.value = true
        }

        viewModelScope.launch {
            // Sequence the loading states and update progress

            // Step 1: Prepare
            _loadingText.value = "Preparing Mazen World..."
            _progress.value = 0.3f
            delay(1000)

            // Step 2: Load Assets
            _loadingText.value = "Loading assets and worlds..."
            _progress.value = 0.65f
            delay(1500)

            // Step 3: Finalizing
            _loadingText.value = "Almost ready to play!"
            _progress.value = 1.0f
            delay(500) // Keep the 100% bar visible briefly

            // Complete
            _isLoaded.value = true
        }
    }
}