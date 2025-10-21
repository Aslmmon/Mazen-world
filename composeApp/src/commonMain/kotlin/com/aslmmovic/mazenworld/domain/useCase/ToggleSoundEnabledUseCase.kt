package com.aslmmovic.mazenworld.domain.useCase

import com.aslmmovic.mazenworld.domain.respository.GameRepository

class ToggleSoundEnabledUseCase(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke() {
        // 1. Load current profile
        val currentProfile = gameRepository.getUserProfile()

        // 2. Toggle the value
        val updatedProfile = currentProfile.copy(
            soundEnabled = !currentProfile.soundEnabled
        )

        // 3. Save the updated profile
        gameRepository.saveUserProfile(updatedProfile)
    }
}