package com.aslmmovic.mazenworld.data.model


import kotlinx.serialization.Serializable

@Serializable // Required for Firestore to automatically map data
data class CategoryDto(
    val id: String = "",
    val title: String = "",
    val iconUrl: String = "",
    val starCost: Int = 0,
    val isLocked: Boolean = true,
    val isPremiumContent: Boolean = false,
)

@Serializable
data class GameQuestionDto(
    // e.g., "Find the letter أ"
    val questionText: String,
    // The main image for the question (e.g., Car image for VEHICLES)
    val questionImageId: String,
    // The four clickable options
    val options: List<GameOptionDto>,
    // ID of the correct option
    val correctAnswerId: String
)

@Serializable
data class GameOptionDto(
    val id: String,
    val text: String? = null,      // The word/letter text (for ALPHABET/VEHICLES)
    val iconUrl: String? = null     // The icon/image url (for ANIMALS/SHAPES)
)
