package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.domain.CategoryItem
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.treesvg
import org.jetbrains.compose.resources.painterResource

// commonMain/presentation/components/LevelNode.kt (Simplified)

@Composable
fun BoxScope.LevelNode(
    item: CategoryItem,
    onNodeClick: (CategoryItem) -> Unit
) {
    // 1. Calculate screen placement (MOCK POSITIONS - You need to adjust these values)
    val positionModifier =
        getCategoryPositionModifier(this, item.id) // 🌟 FIX 2: Pass 'this' (the BoxScope)

    // 2. The Node Container
    Box(
        modifier = Modifier
            .size(80.dp) // Size of the base pedestal
            .then(positionModifier) // Apply fixed position on the map
            .withPressAnimation(onClick = { onNodeClick(item) }), // Use your animated modifier
        contentAlignment = Alignment.Center
    ) {

        // 3. Display Content (Icon or Lock)
        if (item.isLocked) {
            // LOCKED: Show the lock icon and star cost
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(painterResource(Res.drawable.treesvg), contentDescription = "Locked")
                Text(item.starCost.toString(), color = Color.White, fontSize = 16.sp)
            }
        } else {
            // UNLOCKED: Show the actual category icon (Lion, Truck, etc.)
            Image(
                painterResource(item.iconResource),
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize(0.7f)
            )
        }
    }
}


fun BoxScope.getCategoryPositionModifier(scope: BoxScope, id: String): Modifier {
    return when (id) {
        // Modifier.align is now correctly called inside the BoxScope receiver
        "ALPHABET_1" -> Modifier
            .align(Alignment.TopCenter)
            .padding(top = 150.dp)
        "SHAPES_1" -> Modifier
            .align(Alignment.Center)
            .padding(top = 80.dp)
        "VEHICLES_1" -> Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 120.dp, start = 80.dp)

        "ANIMALS_1" -> Modifier
            .align(Alignment.TopEnd)
            .padding(top = 200.dp)
        else -> Modifier.align(Alignment.TopStart)
    }
}