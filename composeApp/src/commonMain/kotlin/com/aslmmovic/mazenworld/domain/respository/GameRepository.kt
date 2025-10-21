package com.aslmmovic.mazenworld.domain.respository

import com.aslmmovic.mazenworld.domain.GameContent
import com.aslmmovic.mazenworld.domain.UserProfile

// domain/repository/GameRepository.kt
interface GameRepository {
    // Content related
    suspend fun getAllContent(): List<GameContent>
    suspend fun getFreemiumContent(): List<GameContent>

    // User profile related (will be implemented via local DB later)
    suspend fun getUserProfile(): UserProfile
    suspend fun saveUserProfile(profile: UserProfile)
}