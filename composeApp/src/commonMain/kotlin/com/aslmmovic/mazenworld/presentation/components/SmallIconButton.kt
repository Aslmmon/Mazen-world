package com.aslmmovic.mazenworld.presentation.components

// commonMain/presentation/ui/components/SmallIconButton.kt

import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.aslmmovic.mazenworld.R
import com.aslmmovic.mazenworld.utils.AudioPlayerManager
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
    var isPressed by remember { mutableStateOf(false) }
// 2. ANIMATION: Smoothly animate the scale
    val scale: Float by animateFloatAsState(
        targetValue = if (isPressed) 0.85f else 1.0f, // Scale down when pressed
        animationSpec = spring(dampingRatio = DampingRatioMediumBouncy)
    )


    Box(
        modifier = modifier
            .size(56.dp)
            .scale(scale) // Apply the animation scale
            .pointerInput(Unit) { // Handles the press/lift visual feedback
                awaitPointerEventScope {
                    while (true) {
                        awaitFirstDown() // Wait for press down
                        isPressed = true // Visual scale down begins
                        val up = waitForUpOrCancellation() // Wait for lift up
                        isPressed = false // Visual scale up begins
                        if (up != null) {
                            AudioPlayerManager.playSound("button_click")
                            onClick()
                        }
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {

        // 2. The Background Image (The "Frame")
        Image(
            painter = backgroundPainter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 3. The Content (The Icon: Music Note, Trophy, etc.)
        // This composable is provided by the caller (e.g., an Icon or another Image)
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