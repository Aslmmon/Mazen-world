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
