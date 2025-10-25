package com.aslmmovic.mazenworld.data.source

// commonMain/data/MockGameData.kt (MOCK QUESTIONS)

import com.aslmmovic.mazenworld.domain.GameOption
import com.aslmmovic.mazenworld.domain.GameQuestion
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.yellow_truck

private val MOCK_VEHICLES_QUESTIONS = listOf(
    // Q1: Find the word "CAR" (Matches Play screen - 1.png reference)
    GameQuestion(
        questionText = "ابحث عن 'السيارة الكبيرة'", // Find the 'Big Car'
        questionImageId = Res.drawable.yellow_truck,
        options = listOf(
            GameOption(id = "O1", text = "قارب", iconResource = Res.drawable.yellow_truck),
            GameOption(id = "O2", text = "طائرة", iconResource = Res.drawable.yellow_truck),
            GameOption(
                id = "O3",
                text = "سيارة",
                iconResource = Res.drawable.yellow_truck
            ), // Correct Answer
            GameOption(id = "O4", text = "هليكوبتر", iconResource = Res.drawable.yellow_truck)
        ),
        correctAnswerId = "O3"
    ),
    // Add 4 more mock questions here...
)

fun getMockQuestionsForCategory(categoryId: String): List<GameQuestion> {
    // Returns a mock list based on the category ID passed from the map
    return when (categoryId) {
        "VEHICLES_1" -> MOCK_VEHICLES_QUESTIONS
        // Add other categories later
        else -> MOCK_VEHICLES_QUESTIONS
    }
}