package com.aslmmovic.mazenworld.presentation.theme

// commonMain/presentation/theme/Color.kt

import androidx.compose.ui.graphics.Color

// --- Core Palette ---
val MazenPrimary = Color(0xFF4CAF50)      // Bright Green (Nature/Trees)
val MazenSecondary = Color(0xFFFF9800)    // Bright Orange (Stars/Accents)
val MazenTertiary = Color(0xFF3A8FB7)    // Bright Orange (Stars/Accents)
val MazenBackground = Color(0xFFF7F7F7)   // Near-White (Clean background)
val MazenSurface = Color(0xFFFFFFFF)      // White (Game cards/Buttons)
val MazenError = Color(0xFFF44336)        // Red (Error/Incorrect feedback)

// --- Light Theme Colors ---
val LightPrimary = MazenPrimary
val LightSecondary = MazenSecondary
val LightTertiary = MazenTertiary
val LightBackground = MazenBackground
val LightSurface = MazenSurface
val LightOnPrimary = Color.White       // Text on Primary background
val LightOnBackground = Color.Black    // Text on Main background

// --- Dark Theme Colors (For completeness, though a kids app might skip it) ---
val DarkPrimary = Color(0xFF66BB6A)       // Lighter Green (Dark mode primary)
val DarkSecondary = Color(0xFFFFB74D)     // Lighter Orange
val DarkBackground = Color(0xFF121212)    // Very dark background
val DarkSurface = Color(0xFF1E1E1E)       // Dark surfaces
val DarkOnPrimary = Color.Black
val DarkOnBackground = Color.White