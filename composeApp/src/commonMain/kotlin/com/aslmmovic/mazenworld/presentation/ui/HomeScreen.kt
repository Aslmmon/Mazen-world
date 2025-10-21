package com.aslmmovic.mazenworld.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aslmmovic.mazenworld.presentation.navigation.Screen
import com.aslmmovic.mazenworld.presentation.ui.viewmodel.HomeViewModel
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.homebg
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

// presentation/ui/HomeScreen.kt (Simplified Structure)
@Composable
fun HomeScreen(navController: NavController) {
    // You'll fetch the ViewModel here using Koin/DI setup
    // val viewModel: HomeViewModel = koinViewModel()

//    val viewModel: HomeViewModel = koinViewModel()
//    val profile by viewModel.profileState.collectAsState() // Observe the state


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
    }

}

@Composable
fun AudioToggleButton(
    isEnabled: Boolean,
    onToggle: () -> Unit,
    activeIconResource: String,
    inactiveIconResource: String
) {
    SmallIconButton(onClick = onToggle) {
        // Use an appropriate icon based on the state
        val icon = if (isEnabled) activeIconResource else inactiveIconResource

        // This still assumes you have setup your resource loading for the icons
//        Icon(
//            painter = painterResource("icon"),
//            contentDescription = if (isEnabled) "Toggle Off" else "Toggle On",
//            tint = Color.Unspecified
//        )
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