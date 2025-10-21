package com.aslmmovic.mazenworld.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslmmovic.mazenworld.domain.UserProfile
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import com.aslmmovic.mazenworld.domain.useCase.ToggleMusicEnabledUseCase
import com.aslmmovic.mazenworld.domain.useCase.ToggleSoundEnabledUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// presentation/viewmodel/HomeViewModel.kt (Simplified)

class HomeViewModel(
    private val gameRepository: GameRepository,
    private val toggleMusicEnabled: ToggleMusicEnabledUseCase,
    private val toggleSoundEnabled: ToggleSoundEnabledUseCase
) : ViewModel() { // Assuming you use a shared ViewModel base class

    // Expose the User Profile state to the UI
//    val profileState: StateFlow<UserProfile> = flow {
//        // We'll use a hot flow (like StateFlow/SharedFlow) in the repository
//        // later, but for now, we poll/load on start.
//        emit(gameRepository.getUserProfile())
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserProfile())


    fun toggleMusic() {
        viewModelScope.launch {
            toggleMusicEnabled()
            // Relaunch the profile flow (or rely on a hot flow from the repo)
        }
    }

    fun toggleSound() {
        viewModelScope.launch {
            toggleSoundEnabled()
            // Relaunch the profile flow
        }
    }
}