package com.aslmmovic.mazenworld.presentation.theme

// commonMain/presentation/theme/Theme.kt

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Define the Light Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    background = LightBackground,
    tertiary = LightTertiary,
    surface = LightSurface,
    error = MazenError,
    onPrimary = LightOnPrimary,
    onBackground = LightOnBackground,
    onSurface = LightOnBackground
)

// Define the Dark Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    background = DarkBackground,
    tertiary = LightTertiary,
    surface = DarkSurface,
    error = MazenError,
    onPrimary = DarkOnPrimary,
    onBackground = DarkOnBackground,
    onSurface = DarkOnBackground
)

@Composable
fun MazenWorldTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Detect system theme for default
    // Dynamic color is typically disabled for specific branding and target audience consistency
    content: @Composable () -> Unit
) {
    // Choose the color scheme based on the selected theme
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = mazenWorldTypography(),
        // shapes = YourShapes,
        content = content
    )
}