package com.aslmmovic.mazenworld.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.homebg
import mazenworld.composeapp.generated.resources.tree
import mazenworld.composeapp.generated.resources.treesvg
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = koinViewModel()
//    val profile by viewModel.profileState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        // 1. Background Image (home.png)
        Image(
            // NOTE: Use the resource that corresponds to your home.png file.
            // Assuming you named the asset 'home' and it's in resources/drawable.
            painter = painterResource(Res.drawable.homebg),
            contentDescription = "Mazen World Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. Placeholder Title
        Text(

            text = "Home Screen",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black, // Ensure text is visible over the background
            modifier = Modifier
                .align(Alignment.TopCenter) // Place at the top center
                .wrapContentSize(Alignment.Center)
                .padding(top = 30.dp)
        )

        // --- Placeholder for Play Button and Side Icons ---
        // For Day 5, we will implement the actual clickable buttons
        // using your image assets on top of this background.
// Star Counter (Top Right)
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(
                    Color.Yellow.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(8.dp)
                ), // Added background for visibility
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Star Icon (You must have this resource file!)
            Icon(
                painter = painterResource(Res.drawable.tree),
                contentDescription = "Stars",
                tint = Color(0xFFFF9800), // Orange tint for the star
                modifier = Modifier.size(24.dp)
            )

            // Star Count (reads persistent value)
//            Text(
//                text = profile.stars.toString(), // <-- USES PERSISTENT DATA
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
        }


        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Star button (Trophy Icon placeholder)
            SmallIconButton(onClick = { /* navigate to Rewards */ }) {
                // ... Icon
            }
        }


    }

}

@Composable
fun SmallIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit // The icon/content inside the button
) {
    // This design mimics the yellow circular buttons seen in your reference images.

    Box(
        modifier = modifier
            .size(56.dp) // Standard size for a small circular button
            .clip(CircleShape) // Makes it a circle
            // A distinctive yellow background
            .background(Color(0xFFFFEE58))
            .clickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}