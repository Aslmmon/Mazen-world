package com.aslmmovic.mazenworld.data.source

// commonMain/data/MockData.kt

import com.aslmmovic.mazenworld.domain.CategoryItem
import mazenworld.composeapp.generated.resources.Res
// Import specific resource identifiers needed for the map nodes
import mazenworld.composeapp.generated.resources.star_icon // Example icon
import mazenworld.composeapp.generated.resources.treesvg


// Static data list mimicking the levels on the map
val MOCK_CATEGORIES = listOf(
    CategoryItem(
        id = "ANIMALS_1",
        title = "Farm Animals",
        // Assuming you have an icon for the cow
        iconResource = Res.drawable.treesvg,
        starCost = 0, // FREE - Always unlocked
        isLocked = false
    ),
    CategoryItem(
        id = "ALPHABET_1",
        title = "First Letters",
        iconResource = Res.drawable.treesvg, // The lion node
        starCost = 0, // FREE - Always unlocked
        isLocked = false
    ),
    CategoryItem(
        id = "SHAPES_1",
        title = "Basic Shapes",
        iconResource = Res.drawable.treesvg, // The starfish node
        starCost = 30, // Requires stars
        isLocked = true
    ),
    CategoryItem(
        id = "VEHICLES_1",
        title = "Big Trucks",
        iconResource = Res.drawable.treesvg, // The truck node
        starCost = 50, // More expensive
        isLocked = true
    ),
    // ... add placeholders for other nodes (Ball, Anvil)
)