package com.aslmmovic.mazenworld.domain

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aslmmovic.mazenworld.data.model.CategoryDto
import org.jetbrains.compose.resources.DrawableResource

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

data class CategoryItem(
    val id: String,
    val title: String,
    // The resource to display on the node (Lion, Truck, Starfish, etc.)
    val iconUrl: String,
    val starCost: Int,
    val isLocked: Boolean = true, // Initial state is locked
    val isPremiumContent: Boolean = false,
)

data class CategoryPosition(
    val alignment: Alignment,
    val paddingHorizontal: Dp = 0.dp,
    val paddingVertical: Dp = 0.dp
)

data class MapState(
    // The list of all levels the user sees on the map
    val categories: List<CategoryDto> = emptyList(),
    // Any error state, though we'll keep this simple for the MVP
    val errorMessage: String? = null
)

// commonMain/domain/model/GameQuestion.kt (MOCK QUESTION STRUCTURE)


data class GameQuestion(
    // e.g., "Find the letter أ"
    val questionText: String,
    // The main image for the question (e.g., Car image for VEHICLES)
    val questionImageId: DrawableResource?,
    // The four clickable options
    val options: List<GameOption>,
    // ID of the correct option
    val correctAnswerId: String
)

data class GameOption(
    val id: String,
    val text: String?,                     // The word/letter text (for ALPHABET/VEHICLES)
    val iconResource: DrawableResource?     // The icon/image (for ANIMALS/SHAPES)
)