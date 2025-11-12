package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput

// No changes to constants
const val soundEffectSfx = "button_click"

fun Modifier.withPressAnimation(
    onClick: () -> Unit,
    playSound: Boolean = true,
    sfxId: String = soundEffectSfx
): Modifier = composed {
    // 1. Remember the interaction source. This is a stable object.
    val interactionSource = remember { MutableInteractionSource() }
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.85f else 1.0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scaleAnimation"
    )

    // 2. Wrap the changing onClick lambda
    val updatedOnClick by rememberUpdatedState(onClick)

    // 3. Chain the modifiers correctly
    this
        .scale(scale) // The scale modifier will be applied when the 'scale' value changes
        .clickable( // Use the standard, highly optimized clickable modifier
            interactionSource = interactionSource,
            indication = null, // Disable the default ripple effect
            onClick = updatedOnClick // Pass the safe, updated onClick
        )
        .pointerInput(interactionSource) { // The pointerInput now only handles the press state
            awaitPointerEventScope {
                while (true) {
                    // This coroutine now ONLY sets the 'isPressed' state.
                    // It does not call onClick().
                    awaitFirstDown(requireUnconsumed = false)
                    isPressed = true
                    waitForUpOrCancellation()
                    isPressed = false
                }
            }
        }
}
