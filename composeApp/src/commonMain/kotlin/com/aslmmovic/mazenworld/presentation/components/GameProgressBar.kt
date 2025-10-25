package com.aslmmovic.mazenworld.presentation.components

// commonMain/presentation/ui/game/GameProgressBar.kt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Assuming you have star and score icon assets:
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.star_icon // Use a star or progress icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun GameProgressBar(currentIndex: Int, totalQuestions: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Display Stars/Score Icon (like the one in your reference image)
        Image(
            painter = painterResource(Res.drawable.star_icon),
            contentDescription = "Current Score",
            modifier = Modifier.size(30.dp)
        )

        Spacer(Modifier.width(8.dp))

        // Display the actual progress count (e.g., 5 / 30)
        Text(
            text = "${currentIndex + 1} / $totalQuestions",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        // You could add a simple LinearProgressIndicator here for visual sweep:
        // LinearProgressIndicator(progress = (currentIndex + 1).toFloat() / totalQuestions)
    }
}