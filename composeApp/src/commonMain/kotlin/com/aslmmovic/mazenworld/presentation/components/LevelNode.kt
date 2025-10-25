package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.domain.CategoryItem
import org.jetbrains.compose.resources.painterResource

// commonMain/presentation/components/LevelNode.kt (Simplified)

@Composable
fun BoxScope.LevelNode(
    item: CategoryItem,
    onNodeClick: (CategoryItem) -> Unit
) {
    Box(
        modifier = Modifier
            .then(applyCategoryPosition(item)) // Apply position data
            .size(80.dp) // Size of the base pedestal
            .withPressAnimation(onClick = { onNodeClick(item) }), // Use your animated modifier
        contentAlignment = Alignment.Center
    ) {

        // 3. Display Content (Icon or Lock)
        if (item.isLocked) {
            // LOCKED: Show the lock icon and star cost
            //   Text("Locked", color = Color.Black, fontSize = 35.sp)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                //  Icon(painterResource(Res.drawable.treesvg), contentDescription = "Locked")
                Text(item.starCost.toString(), color = Color.White, fontSize = 16.sp)
            }
        } else {

            Column {
                Image(
                    painterResource(item.iconResource),
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxSize(1f)
                )
                Text(item.id, color = Color.White, fontSize = 14.sp)

            }
            // UNLOCKED: Show the actual category icon (Lion, Truck, etc.)

        }
    }
}


fun BoxScope.applyCategoryPosition(item: CategoryItem): Modifier {
    return with(item.position) { // Use 'with' on the position data
        Modifier
            .align(alignment)
            .padding(top = paddingVertical, start = paddingHorizontal)
    }
}

