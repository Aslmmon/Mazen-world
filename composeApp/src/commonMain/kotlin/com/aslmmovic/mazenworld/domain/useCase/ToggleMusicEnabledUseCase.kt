package com.aslmmovic.mazenworld.domain.useCase

import com.aslmmovic.mazenworld.domain.respository.GameRepository

// domain/usecase/ToggleMusicEnabledUseCase.kt (and create a similar one for Sound)

class ToggleMusicEnabledUseCase(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke() {
        // 1. Load current profile
        val currentProfile = gameRepository.getUserProfile()

        // 2. Toggle the value
        val updatedProfile = currentProfile.copy(
            musicEnabled = !currentProfile.musicEnabled
        )

        // 3. Save the updated profile
        gameRepository.saveUserProfile(updatedProfile)
    }
}