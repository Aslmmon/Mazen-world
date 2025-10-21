package com.aslmmovic.mazenworld.data.repository

import com.aslmmovic.mazenworld.domain.GameContent
import com.aslmmovic.mazenworld.domain.UserProfile
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

// data/repository/MockGameRepository.kt
class MockGameRepository : GameRepository {
    private val mockContent = listOf(
        GameContent(
            id = "A1",
            nameArabic = "P_A",
            imageUrl = "mock/A.png",
            soundUrl = "mock/a.mp3",
            isPremium = false
        ),
        GameContent(
            id = "B2",
            nameArabic = "P_B",
            imageUrl = "mock/B.png",
            soundUrl = "mock/b.mp3",
            isPremium = false
        ),
        GameContent(
            id = "C3",
            nameArabic = "P_C",
            imageUrl = "mock/C.png",
            soundUrl = "mock/c.mp3",
            isPremium = false
        ),
        GameContent(
            id = "D4",
            nameArabic = "P_D",
            imageUrl = "mock/D.png",
            soundUrl = "mock/d.mp3",
            isPremium = true,
            starCost = 50
        ),
        GameContent(
            id = "E5",
            nameArabic = "P_E",
            imageUrl = "mock/E.png",
            soundUrl = "mock/e.mp3",
            isPremium = true,
            starCost = 50
        )
        // ... add the rest of the 28 letter placeholders
    )

    // Using a simple MutableStateFlow to simulate a live, changing profile (like a database)
    private val profileFlow = MutableStateFlow(UserProfile())

    override suspend fun getAllContent(): List<GameContent> = mockContent

    override suspend fun getFreemiumContent(): List<GameContent> =
        mockContent.filter { !it.isPremium }

    override suspend fun getUserProfile(): UserProfile = profileFlow.first()

    override suspend fun saveUserProfile(profile: UserProfile) {
        profileFlow.value = profile
    }
}