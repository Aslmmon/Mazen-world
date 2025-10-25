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

class HomeViewModel(
    private val gameRepository: GameRepository,
    private val toggleMusicEnabled: ToggleMusicEnabledUseCase,
    private val toggleSoundEnabled: ToggleSoundEnabledUseCase
) : ViewModel() { // Assuming you use a shared ViewModel base class


    // The single source of state for the UI, streamed directly from the Repository Flow.
    val profileState: StateFlow<UserProfile> = gameRepository.getUserProfile()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = UserProfile(stars = 16, musicEnabled = true, soundEnabled = true)
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