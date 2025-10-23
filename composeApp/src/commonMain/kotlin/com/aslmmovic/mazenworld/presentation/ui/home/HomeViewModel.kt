package com.aslmmovic.mazenworld.presentation.ui.home

import ToggleMusicEnabledUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.domain.UserProfile
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import com.aslmmovic.mazenworld.domain.useCase.ToggleSoundEnabledUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// presentation/viewmodel/HomeViewModel.kt (Simplified)

class HomeViewModel(
    private val gameRepository: GameRepository,
    private val toggleMusicEnabled: ToggleMusicEnabledUseCase,
    private val toggleSoundEnabled: ToggleSoundEnabledUseCase
) : ViewModel() { // Assuming you use a shared ViewModel base class

    // Using a simple load/refresh mechanism for MVP
//    private val _profileState = MutableStateFlow(UserProfile())
//    val profileState: StateFlow<UserProfile> = _profileState

    val profileState: StateFlow<UserProfile> = gameRepository.getUserProfile()
        .stateIn(
            scope = viewModelScope,
            // Keep the flow active while subscribed, stopping after 5 seconds of inactivity.
            started = SharingStarted.WhileSubscribed(5000),
            // Initial value before the first emission from the repository.
            initialValue = UserProfile()
        )


    fun toggleMusic() {
        viewModelScope.launch {
            toggleMusicEnabled()
        }
    }

    fun toggleSound() {
        viewModelScope.launch {
            toggleSoundEnabled()
        }
    }
}