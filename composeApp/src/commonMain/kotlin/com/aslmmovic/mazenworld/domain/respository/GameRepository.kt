package com.aslmmovic.mazenworld.domain.respository

import com.aslmmovic.mazenworld.data.source.MockState
import com.aslmmovic.mazenworld.domain.UserProfile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface GameRepository {
    fun getUserProfile(): Flow<UserProfile>
    suspend fun saveMusicState(enabled: Boolean)
    suspend fun saveSoundState(enabled: Boolean)
}

class MockGameRepository : GameRepository {
    override fun getUserProfile(): Flow<UserProfile> = combine(
        MockState.stars,
        MockState.musicEnabled,
        MockState.soundEnabled
    ) { stars, musicEnabled, soundEnabled ->
        UserProfile(stars, musicEnabled, soundEnabled)
    }
    override suspend fun saveMusicState(enabled: Boolean) {
        delay(50) // Simulate write delay
        MockState.updateMusic(enabled)
    }

    override suspend fun saveSoundState(enabled: Boolean) {
        delay(50) // Simulate write delay
        MockState.updateSound(enabled)
    }
}