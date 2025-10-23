package com.aslmmovic.mazenworld.data.source

import kotlinx.coroutines.flow.Flow

interface PreferencesService {
    // Read operations return Flow for observation
    fun getStars(): Flow<Int>
    fun getMusicEnabled(): Flow<Boolean>
    fun getSoundEnabled(): Flow<Boolean>
    fun isPremium(): Flow<Boolean>

    // Write operations are suspending
    suspend fun saveStars(stars: Int)
    suspend fun setMusicEnabled(enabled: Boolean)
    suspend fun setSoundEnabled(enabled: Boolean)
    suspend fun setPremium(status: Boolean) // Added setPremium for completeness
}