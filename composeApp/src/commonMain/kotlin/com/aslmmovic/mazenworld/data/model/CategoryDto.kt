package com.aslmmovic.mazenworld.data.model


import kotlinx.serialization.Serializable

@Serializable // Required for Firestore to automatically map data
data class CategoryDto(
    val id: String = "",
    val title: String = "",
    val iconResource: String = "", // In Firestore, this will be a string like "animals_icon.xml"
    val starCost: Int = 0,
    val isLocked: Boolean = true,
    val isPremiumContent: Boolean = false,
)
