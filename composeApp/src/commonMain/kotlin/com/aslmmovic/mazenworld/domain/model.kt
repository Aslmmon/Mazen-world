package com.aslmmovic.mazenworld.domain

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aslmmovic.mazenworld.data.model.CategoryDto
import org.jetbrains.compose.resources.DrawableResource

// domain/model/UserProfile.kt
data class UserProfile(
    val stars: Int = 0,
    val isPremium: Boolean = false,
    val soundEnabled: Boolean = true,
    val musicEnabled: Boolean = true

)
