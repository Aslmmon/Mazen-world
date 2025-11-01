package com.aslmmovic.mazenworld.data.source

// commonMain/data/MockData.kt

// Import specific resource identifiers needed for the map nodes
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.aslmmovic.mazenworld.domain.CategoryItem
import com.aslmmovic.mazenworld.domain.CategoryPosition
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.car_2
import mazenworld.composeapp.generated.resources.category_alfabet
import mazenworld.composeapp.generated.resources.category_shape
import mazenworld.composeapp.generated.resources.category_vehicles
import mazenworld.composeapp.generated.resources.ship
import mazenworld.composeapp.generated.resources.tiger


// Static data list mimicking the levels on the map
val MOCK_CATEGORIES = listOf(
    CategoryItem(
        id = "ANIMALS_1",
        title = "الحيوانات",
        // Assuming you have an icon for the cow
        iconResource = Res.drawable.tiger,
        starCost = 0, // FREE - Always unlocked
        isLocked = false,
        position = CategoryPosition(
            alignment = Alignment.BottomCenter,
            paddingHorizontal = 20.dp,
            paddingVertical = 100.dp
        )

    ),
    CategoryItem(
        id = "ALPHABET_1",
        title = "الحروف",
        iconResource = Res.drawable.category_alfabet, // The lion node
        starCost = 0,
        isLocked = false,
        position = CategoryPosition(
            alignment = Alignment.TopStart,
            paddingHorizontal = 150.dp,
            paddingVertical = 150.dp
        )

    ),
    CategoryItem(
        id = "SHAPES_1",
        title = "الأشكال",
        iconResource = Res.drawable.ship, // The starfish node
        starCost = 30, // Requires stars
        isLocked = true,
        position = CategoryPosition(
            alignment = Alignment.TopStart,
            paddingHorizontal = 150.dp,
            paddingVertical = 250.dp
        )
    ),
    CategoryItem(
        id = "VEHICLES_1",
        title = "السيارات",
        iconResource = Res.drawable.car_2, // The truck node
        starCost = 50, // More expensive
        isLocked = true,
        position = CategoryPosition(
            alignment = Alignment.Center,
            paddingVertical = 20.dp
        )
    ),
    CategoryItem(
        id = "VEHICLES_1",
        title = "الألوان",
        iconResource = Res.drawable.category_shape, // The truck node
        starCost = 50, // More expensive
        isLocked = true,
        position = CategoryPosition(
            alignment = Alignment.Center,
            paddingVertical = 20.dp
        )
    ),
)