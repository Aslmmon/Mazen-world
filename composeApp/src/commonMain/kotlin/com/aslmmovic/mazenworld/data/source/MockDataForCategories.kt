package com.aslmmovic.mazenworld.data.source

// commonMain/data/MockData.kt

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aslmmovic.mazenworld.domain.CategoryItem
import com.aslmmovic.mazenworld.domain.CategoryPosition
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.category_alfabet
import mazenworld.composeapp.generated.resources.category_animals
import mazenworld.composeapp.generated.resources.category_shape
import mazenworld.composeapp.generated.resources.category_vehicles
// Import specific resource identifiers needed for the map nodes
import mazenworld.composeapp.generated.resources.star_icon // Example icon
import mazenworld.composeapp.generated.resources.treesvg



fun getCategoryPositionModifier(scope: BoxScope, id: String): Modifier {
    return with(scope) {
        when (id) {
//            "ALPHABET_1" -> Modifier
//                .align(Alignment.TopStart)
//                .padding(top = 150.dp, start = 150.dp)
//            "SHAPES_1" -> Modifier
//                .align(Alignment.TopStart)
//                .padding(top = 250.dp, start = 150.dp)
//            "VEHICLES_1" -> Modifier
//                .align(Alignment.BottomCenter)
//                .padding(bottom = 25.dp)
//            "ANIMALS_1" -> Modifier
//                .align(Alignment.BottomCenter)
//                .padding(bottom = 100.dp, start = 20.dp)

            else -> Modifier.align(Alignment.TopStart)
        }
    }
}

// Static data list mimicking the levels on the map
val MOCK_CATEGORIES = listOf(
    CategoryItem(
        id = "ANIMALS_1",
        title = "Farm Animals",
        // Assuming you have an icon for the cow
        iconResource = Res.drawable.category_alfabet,
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
        title = "First Letters",
        iconResource = Res.drawable.category_alfabet, // The lion node
        starCost = 0, // FREE - Always unlocked
        isLocked = false,
        position = CategoryPosition(
            alignment = Alignment.TopStart,
            paddingHorizontal = 150.dp,
            paddingVertical = 150.dp
        )

    ),
    CategoryItem(
        id = "SHAPES_1",
        title = "Basic Shapes",
        iconResource = Res.drawable.category_shape, // The starfish node
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
        title = "Big Trucks",
        iconResource = Res.drawable.category_vehicles, // The truck node
        starCost = 50, // More expensive
        isLocked = true,
        position = CategoryPosition(
            alignment = Alignment.Center,
            paddingVertical = 20.dp
        )
    ),
    // ... add placeholders for other nodes (Ball, Anvil)
)