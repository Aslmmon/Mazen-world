package com.aslmmovic.mazenworld.data.source

// commonMain/data/MockGameData.kt (MOCK QUESTIONS)

import com.aslmmovic.mazenworld.domain.GameOption
import com.aslmmovic.mazenworld.domain.GameQuestion
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.aeroplane
import mazenworld.composeapp.generated.resources.car_2
import mazenworld.composeapp.generated.resources.train
import mazenworld.composeapp.generated.resources.train_red
import mazenworld.composeapp.generated.resources.underwater
import mazenworld.composeapp.generated.resources.underwater_2
import mazenworld.composeapp.generated.resources.white_car
import mazenworld.composeapp.generated.resources.yellow_truck

private val MOCK_VEHICLES_QUESTIONS = listOf(
    // Q1: Find the word "CAR" (Matches Play screen - 1.png reference)
    GameQuestion(
        questionText = "ابحث عن 'السيارة الكبيرة'", // Find the 'Big Car'
        questionImageId = Res.drawable.yellow_truck,
        options = listOf(
            GameOption(id = "O1", text = "قارب", iconResource = Res.drawable.underwater),
            GameOption(id = "O2", text = "طائرة", iconResource = Res.drawable.aeroplane),
            GameOption(
                id = "O3",
                text = "سيارة",
                iconResource = Res.drawable.yellow_truck
            ), // Correct Answer
            GameOption(id = "O4", text = "هليكوبتر", iconResource = Res.drawable.aeroplane)
        ),
        correctAnswerId = "O3"
    ),

    // Q2: (Image to Image Match - Find the same Plane)
    GameQuestion(
        questionText = "أين هي 'الطائرة '؟", // Where is the 'Red Plane'?
        questionImageId = Res.drawable.aeroplane, // Image prompt: Red Plane
        options = listOf(
            GameOption(id = "O1", text = null, iconResource = Res.drawable.yellow_truck),
            GameOption(id = "O2", text = null, iconResource = Res.drawable.underwater),
            GameOption(id = "O3", text = null, iconResource = Res.drawable.underwater_2),
            GameOption(
                id = "O4",
                text = null,
                iconResource = Res.drawable.yellow_truck
            ) // Correct Answer: Red Plane Image
        ),
        correctAnswerId = "O4"
    ),

    GameQuestion(
        questionText = "اختر صورة 'القارب'", // Choose the picture of the 'Boat'
        questionImageId = Res.drawable.underwater, // Image prompt: Blue Boat
        options = listOf(
            GameOption(id = "O1", text = "شاحنة", iconResource = Res.drawable.underwater),
            GameOption(
                id = "O2",
                text = "قارب",
                iconResource = Res.drawable.underwater
            ), // Correct Answer: Word 'قارب'
            GameOption(id = "O3", text = "سيارة", iconResource = Res.drawable.car_2),
            GameOption(id = "O4", text = "هليكوبتر", iconResource = Res.drawable.train_red)
        ),
        correctAnswerId = "O2"
    ),

    GameQuestion(
        questionText = "أي واحدة هي 'الشاحنة'؟", // Which one is the 'Truck'?
        questionImageId = Res.drawable.train, // Image prompt: Yellow Truck
        options = listOf(
            GameOption(
                id = "O1",
                text = "شاحنة",
                iconResource = Res.drawable.train
            ), // Correct Answer: Word 'شاحنة'
            GameOption(id = "O2", text = "طائرة", iconResource = Res.drawable.underwater_2),
            GameOption(id = "O3", text = "قارب", iconResource = Res.drawable.train_red),
            GameOption(id = "O4", text = "سيارة", iconResource = Res.drawable.car_2)
        ),
        correctAnswerId = "O1"
    ),

    // Q5: (Image to Image Match - Find the same Car)
    GameQuestion(
        questionText = "اضغط على الصورة التي تطابق 'السيارة'", // Press the image that matches 'the Car'
        questionImageId = Res.drawable.car_2, // Image prompt: Purple Car
        options = listOf(
            GameOption(id = "O1", text = null, iconResource = Res.drawable.underwater),
            GameOption(
                id = "O2",
                text = null,
                iconResource = Res.drawable.car_2
            ), // Correct Answer: Purple Car Image
            GameOption(id = "O3", text = null, iconResource = Res.drawable.underwater_2),
            GameOption(id = "O4", text = null, iconResource = Res.drawable.train)
        ),
        correctAnswerId = "O2"
    ),
)

fun getMockQuestionsForCategory(categoryId: String): List<GameQuestion> {
    // Returns a mock list based on the category ID passed from the map
    return when (categoryId) {
        "VEHICLES_1" -> MOCK_VEHICLES_QUESTIONS
        // Add other categories later
        else -> MOCK_VEHICLES_QUESTIONS
    }
}