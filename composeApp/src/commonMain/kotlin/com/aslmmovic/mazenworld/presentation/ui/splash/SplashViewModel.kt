package com.aslmmovic.mazenworld.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// presentation/viewmodel/SplashViewModel.kt

class SplashViewModel() : ViewModel() {
    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    // State to hold the current loading message
    private val _loadingStatus = MutableStateFlow(LoadingStatus.INITIALIZING)
    val loadingStatus: StateFlow<LoadingStatus> = _loadingStatus

    // 🌟 NEW: Progress value (Float from 0.0f to 1.0f)
    private val _progress = MutableStateFlow(0.0f)
    val progress: StateFlow<Float> = _progress


    init {
        viewModelScope.launch {
            // Sequence the loading states and update progress
            // Step 1: Prepare
            _loadingStatus.value = LoadingStatus.PREPARING
            _progress.value = 0.3f
            delay(1000)

            // Step 2: Load Assets
            _loadingStatus.value = LoadingStatus.LOADING_ASSETS
            _progress.value = 0.65f
            delay(1500)

            // Step 3: Finalizing
            _loadingStatus.value = LoadingStatus.ALMOST_READY
            _progress.value = 1.0f
            delay(500)

            // Step 4: Complete and navigate
            _loadingStatus.value = LoadingStatus.COMPLETE
            _isDataLoaded.value = true

        }
    }
}

enum class LoadingStatus(val stringId: String) {
    INITIALIZING("status_initializing"),
    PREPARING("status_preparing"),
    LOADING_ASSETS("status_loading_assets"),
    ALMOST_READY("status_almost_ready"),
    COMPLETE("status_complete") // Use this state to trigger navigation
}