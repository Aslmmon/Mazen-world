package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.yellow_frame
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SmallIconButton(
    onClick: () -> Unit,
    backgroundPainter: Painter = painterResource(Res.drawable.yellow_frame),
    contentDescription: String,
    modifier: Modifier = Modifier,
    icon: DrawableResource
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .withPressAnimationAndSound(onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = backgroundPainter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize(1f) // Shrink the inner icon slightly within the button area
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon), // <-- The actual trophy icon
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}