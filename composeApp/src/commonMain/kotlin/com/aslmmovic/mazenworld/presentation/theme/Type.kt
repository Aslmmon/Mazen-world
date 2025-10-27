package com.aslmmovic.mazenworld.presentation.theme

// commonMain/presentation/theme/Type.kt

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.lalezar_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

// Define the custom font family based on your asset


@OptIn(ExperimentalResourceApi::class)
@Composable
fun mazenFontFamily() = FontFamily(
    Font(
        Res.font.lalezar_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resource = Res.font.lalezar_regular,
        weight = FontWeight.Bold
    )
)

// Define the Typography object
@Composable
fun mazenWorldTypography() = Typography().run {
    val fontFamily = mazenFontFamily()
    Typography(


        // H1/Display (For big titles like "Mazen World" or large letters)
        displayLarge = androidx.compose.ui.text.TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
        ),
        // Standard Body Text (For question prompts and interface text)
        bodyLarge = androidx.compose.ui.text.TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
        ),
        // Button Text
        labelLarge = androidx.compose.ui.text.TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
        )
        // Add other styles (headline, title, etc.) as needed

    )
}