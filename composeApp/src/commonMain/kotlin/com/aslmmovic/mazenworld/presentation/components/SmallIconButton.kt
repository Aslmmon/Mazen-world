package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aslmmovic.mazenworld.presentation.ui.settings.SettingsViewModel
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.music_icon_off
import mazenworld.composeapp.generated.resources.music_icon_on
import mazenworld.composeapp.generated.resources.yellow_frame
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SmallIconButton(
    onClick: () -> Unit,
    backgroundPainter: Painter = painterResource(Res.drawable.yellow_frame),
    contentDescription: String,
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    isAnimated: Boolean = true
) {

    val infiniteTransition = rememberInfiniteTransition(label = "icon-animation")

    // 2. Animate the horizontal offset (X position)
    val offsetX by infiniteTransition.animateFloat(
        initialValue = -4f, // Start position (move left by 4 dp)
        targetValue = 4f,   // End position (move right by 4 dp)
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, delayMillis = 200),
            repeatMode = androidx.compose.animation.core.RepeatMode.Reverse // Reverse the animation to go back and forth
        ),
        label = "offsetX"
    )

    Box(
        modifier = modifier
            .size(56.dp)
            .withPressAnimation(onClick),
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
                modifier = Modifier
                    .size(35.dp)
                    .offset(x = if (isAnimated) offsetX.dp else 0.dp) // Apply the animated offset
            )
        }
    }
}

/**
 * A self-contained, reusable button that handles its own logic for
 * toggling the background music. It observes the shared SettingsViewModel.
 */
@Composable
fun MusicToggleButton(modifier: Modifier = Modifier) {
    // 1. All the logic is now encapsulated inside this component.
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val isMuted by settingsViewModel.isMuted.collectAsStateWithLifecycle()
    val onMusicClick = remember(settingsViewModel) {
        { settingsViewModel.toggleMute() }
    }

    // 2. It uses a standard SmallIconButton to render itself.
    SmallIconButton(
        onClick = onMusicClick,
        contentDescription = "Toggle Music",
        icon = if (isMuted) Res.drawable.music_icon_off else Res.drawable.music_icon_on,
        isAnimated = true,
        modifier = modifier
    )
}