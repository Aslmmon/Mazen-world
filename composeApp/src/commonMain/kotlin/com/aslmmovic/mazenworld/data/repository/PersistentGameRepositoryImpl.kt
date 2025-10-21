package com.aslmmovic.mazenworld.data.repository

import com.aslmmovic.mazenworld.data.source.UserLocalDataSource
import com.aslmmovic.mazenworld.domain.GameContent
import com.aslmmovic.mazenworld.domain.UserProfile
import com.aslmmovic.mazenworld.domain.respository.GameRepository

// data/repository/PersistentGameRepositoryImpl.kt (commonMain)

class PersistentGameRepositoryImpl(
    private val localDataSource: UserLocalDataSource
) : GameRepository {

    // Note: This replaces the MockGameRepository's in-memory state.

    override suspend fun getUserProfile(): UserProfile {
        return localDataSource.loadProfile()
    }

    override suspend fun saveUserProfile(profile: UserProfile) {
        localDataSource.saveProfile(profile)
    }

    // Content fetching can still use the mock data for now
    override suspend fun getAllContent(): List<GameContent> {
        return mutableListOf()
    }

    override suspend fun getFreemiumContent(): List<GameContent> {
        return mutableListOf<GameContent>().filter { !it.isPremium }
    }
}