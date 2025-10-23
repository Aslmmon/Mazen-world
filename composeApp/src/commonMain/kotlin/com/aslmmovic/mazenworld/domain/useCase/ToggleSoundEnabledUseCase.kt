package com.aslmmovic.mazenworld.domain.useCase

import com.aslmmovic.mazenworld.domain.respository.GameRepository
import kotlinx.coroutines.flow.first

class ToggleSoundEnabledUseCase(
    private val gameRepository: GameRepository,

) {
    suspend operator fun invoke() {
        // 1. Load current profile
        //    This uses the asynchronous architecture correctly.
        val currentProfile = gameRepository.getUserProfile().first()

        // 2. Toggle the value.
        val newState = !currentProfile.musicEnabled

    }
}