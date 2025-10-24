package com.aslmmovic.mazenworld.presentation.components

// commonMain/presentation/ui/components/SmallIconButton.kt

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    // 1. The main container is a Box that holds the background image
    Box(
        modifier = modifier
            .size(56.dp)
            .clickable(
                onClick = onClick,
                // Good for animation: suppresses the default Android ripple if needed
                interactionSource = remember { MutableInteractionSource() },
                indication = null // Removes the visual feedback (ripple/shadow)
            ),
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