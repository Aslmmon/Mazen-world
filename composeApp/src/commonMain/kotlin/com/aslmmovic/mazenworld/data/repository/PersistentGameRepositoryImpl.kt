package com.aslmmovic.mazenworld.data.repository

import com.aslmmovic.mazenworld.data.source.PreferencesService
import com.aslmmovic.mazenworld.domain.GameContent
import com.aslmmovic.mazenworld.domain.UserProfile
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

// data/repository/PersistentGameRepositoryImpl.kt (commonMain)

class PersistentGameRepositoryImpl(
    private val preferencesService: PreferencesService // Now inject the new service
     ) : GameRepository {

    // Note: This replaces the MockGameRepository's in-memory state.

    val userProfileFlow: Flow<UserProfile> = combine(
        preferencesService.getStars(),
        preferencesService.isPremium(),
        preferencesService.getSoundEnabled(),
        preferencesService.getMusicEnabled()
    ) { stars, isPremium, sound, music ->
        UserProfile(stars, isPremium, sound, music)
    }

    override fun getUserProfile(): Flow<UserProfile>  = userProfileFlow

    override suspend fun saveUserProfile(profile: UserProfile) {
        preferencesService.saveStars(profile.stars)
    }

    // Content fetching can still use the mock data for now
    override suspend fun getAllContent(): List<GameContent> {
        return mutableListOf()
    }

    override suspend fun getFreemiumContent(): List<GameContent> {
        return mutableListOf<GameContent>().filter { !it.isPremium }
    }

}