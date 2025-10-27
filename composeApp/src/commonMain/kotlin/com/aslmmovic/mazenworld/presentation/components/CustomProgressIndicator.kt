package com.aslmmovic.mazenworld.presentation.components

// commonMain/presentation/components/CustomProgressIndicator.kt

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomRoundedProgressIndicator(
    progressValue: Float, // The value from your SplashViewModel (0.0f to 1.0f)
    modifier: Modifier = Modifier,
    // Styling parameters
    trackOutlineColor: Color = Color(0xFF007BFF), // Deep Blue outline
    progressColor: Color = Color(0xFFE1C800),     // Lime Green progress
    trackBackgroundColor: Color = Color.LightGray.copy(alpha = 0.5f), // Light interior
    cornerRadius: Dp = 25.dp
) {
    val roundedShape = RoundedCornerShape(cornerRadius)

    Box(
        modifier = modifier
            // 1. Outer styling: Defines the size and the blue rounded border
            .height(30.dp) // Use fixed height from your request
            .border(1.dp, trackOutlineColor, roundedShape) // Blue Outline
            .background(trackBackgroundColor, roundedShape) // Light gray background
            .clip(roundedShape) // Clip content inside the border
    ) {
        // 2. Linear Progress Indicator (The Green filling)
        LinearProgressIndicator(
            progress = progressValue,
            // The indicator itself fills the entire Box
            modifier = Modifier.fillMaxSize(),
            // Use the specified colors
            color = progressColor,
            trackColor = Color.Transparent, // Make the track transparent so the Box background shows
            strokeCap = StrokeCap.Round // Rounded end to the progress bar itself
        )


    }
}