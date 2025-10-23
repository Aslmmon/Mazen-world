package com.aslmmovic.mazenworld.domain.respository

import com.aslmmovic.mazenworld.domain.GameContent
import com.aslmmovic.mazenworld.domain.UserProfile
import kotlinx.coroutines.flow.Flow

// domain/repository/GameRepository.kt
interface GameRepository {
    // Content related
    suspend fun getAllContent(): List<GameContent>
    suspend fun getFreemiumContent(): List<GameContent>

    // User profile related (will be implemented via local DB later)
    fun getUserProfile(): Flow<UserProfile>
    suspend fun saveUserProfile(profile: UserProfile)
}