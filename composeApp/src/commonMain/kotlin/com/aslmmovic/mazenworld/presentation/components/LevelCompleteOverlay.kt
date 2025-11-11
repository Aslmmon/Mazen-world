package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.treesvg


@Composable
fun BoxScope.LevelCompleteOverlay(onBackClick: () -> Unit, score: Int, totalQuestions: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .align(Alignment.Center),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                "المستوى اكتمل!",
                fontSize = 36.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text("لقد كسبت 25 نجمة!", fontSize = 24.sp, color = Color.Yellow)
            Text("نقاطك: $score/$totalQuestions", fontSize = 18.sp, color = Color.LightGray)

            SmallIconButton(
                onClick = onBackClick,
                contentDescription = "Return to Map",
                icon = Res.drawable.treesvg,
                isAnimated = true // A great place for an animation
            )
        }
    }
}
