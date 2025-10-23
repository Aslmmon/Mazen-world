package com.aslmmovic.mazenworld.domain

// domain/model/GameContent.kt
data class GameContent(
    val id: String, // e.g., "ALIF" for the letter Alif
    val nameArabic: String, // Placeholder for "ألف" or "A"
    val imageUrl: String, // Placeholder for "asset_alif_image.png"
    val soundUrl: String, // Placeholder for "sound_alif.mp3"
    val isPremium: Boolean,
    val starCost: Int = 0 // Cost to unlock if applicable
)

// domain/model/UserProfile.kt
data class UserProfile(
    val stars: Int = 0,
    val isPremium: Boolean = false,
    val soundEnabled: Boolean = true,
    val musicEnabled: Boolean = true


)