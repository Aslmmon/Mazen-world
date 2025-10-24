package com.aslmmovic.mazenworld.presentation.components

// commonMain/presentation/components/CustomModifiers.kt

import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import com.aslmmovic.mazenworld.utils.AudioPlayerManager

// Define the reusable modifier function
fun Modifier.withPressAnimationAndSound(
    onClick: () -> Unit,
    sfxId: String = "button_click" // Default sound effect
): Modifier = composed {
    var isPressed by remember { mutableStateOf(false) }

    val scale: Float by animateFloatAsState(
        targetValue = if (isPressed) 0.85f else 1.0f, // Scale down to 85% when pressed
        animationSpec = spring(dampingRatio = DampingRatioMediumBouncy)
    )
    // Apply the scale and the touch logic
    this
        .scale(scale)
        .pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    awaitFirstDown()
                    isPressed = true // Start visual scale down
                    val up = waitForUpOrCancellation()
                    isPressed = false // Start visual scale up
                    if (up != null) {
                        // Play sound and execute navigation/logic
                        AudioPlayerManager.playSound(sfxId)
                        onClick()
                    }
                }
            }
        }
}