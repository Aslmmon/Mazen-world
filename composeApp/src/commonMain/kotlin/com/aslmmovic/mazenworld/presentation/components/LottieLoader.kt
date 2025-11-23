package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import mazenworld.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LottieLoader(
    modifier: Modifier = Modifier,
    file: String,
    iterations: Int = LottieConstants.IterateForever
) {
    var jsonString by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(file) {
        try {
            // Load the file from composeResources/files/
            val bytes = Res.readBytes("files/lottie/$file")
            jsonString = bytes.decodeToString()
        } catch (e: Exception) {
            e.printStackTrace(
        }
    }

    jsonString?.let { json ->
        val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(json))
        
        LottieAnimation(
            composition = composition,
            iterations = iterations,
            modifier = modifier
        )
    }
}