package com.aslmmovic.mazenworld.domain.useCase

import com.aslmmovic.mazenworld.domain.respository.GameRepository
import kotlinx.coroutines.flow.first

class ToggleSoundEnabledUseCase(
    private val gameRepository: GameRepository,

) {
    suspend operator fun invoke() {
        val currentProfile = gameRepository.getUserProfile().first()
        gameRepository.saveSoundState(!currentProfile.soundEnabled)
    }
}