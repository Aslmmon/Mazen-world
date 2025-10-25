package com.aslmmovic.mazenworld.presentation.ui.categories

// commonMain/presentation/ui/categories/CategoryMapScreen.kt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aslmmovic.mazenworld.presentation.components.LevelNode
import com.aslmmovic.mazenworld.presentation.components.SmallIconButton
import com.aslmmovic.mazenworld.presentation.navigation.Screen
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.back_icon
import mazenworld.composeapp.generated.resources.category_map
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CategoryMapScreen(navController: NavController) {
    // 1. Get the ViewModel and observe the live map state
    val viewModel: CategoryMapViewModel = koinViewModel()
    val mapState by viewModel.mapState.collectAsState()

    // 2. Observe messages (for error/success popups)
    val message by viewModel.message.collectAsState(initial = null)

    // Use the main container (Box) for layering the background and nodes
    Box(modifier = Modifier.fillMaxSize()) {

        // --- 3. Background Image (The Trees and River) ---
        Image(
            painter = painterResource(Res.drawable.category_map), // Assuming this asset exists
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )


        SmallIconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp),
            contentDescription = "back",
            icon = Res.drawable.back_icon

        )
        // --- 4. Loop through categories and place nodes ---
        mapState.categories.forEach { item ->
            // LevelNode will place the icon, cost, and handle the click logic
            LevelNode(
                item = item,
                onNodeClick = { clickedItem ->
                    // Attempt to unlock or navigate
                    if (clickedItem.isLocked) {
                        viewModel.attemptUnlock(clickedItem)
                    } else {
                        // Navigate to the Game Screen when unlocked
                        navController.navigate(Screen.PlayScreen.route + "/${clickedItem.id}")

                    }
                }
            )
        }

        // --- 5. Display Feedback Message ---
        message?.let { msg ->
            // Display a simple Snackbar or Dialog for unlock success/failure
            Text(
                msg,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                color = Color.Red
            )
        }

        // --- 6. Back Button (Top Left) ---
        // (You would implement a SmallIconButton here to navigate back)
    }
}