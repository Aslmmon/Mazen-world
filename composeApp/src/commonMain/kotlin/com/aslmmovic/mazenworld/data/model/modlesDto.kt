package com.aslmmovic.mazenworld.data.model


import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Int = 1,
    val title: String = "",
    val iconUrl: String = "",
    val isLocked: Boolean = true,
)

@Serializable
data class GameQuestionDto(
    val categoryId: Int? = null,
    val questionText: String,
    val questionImageUrl: String,
    val options: List<GameOptionDto>,
    val correctAnswerId: String
)

@Serializable
data class GameOptionDto(
    val id: String,
    val text: String? = null,
    val iconUrl: String? = null
)
