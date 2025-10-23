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
            val minDelay = launch { delay(1000) }
            minDelay.join()
            _isDataLoaded.value = true
        }
    }
}