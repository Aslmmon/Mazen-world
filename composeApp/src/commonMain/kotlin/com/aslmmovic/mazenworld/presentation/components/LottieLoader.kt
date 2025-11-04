package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieLoader(
    modifier: Modifier = Modifier,
    // Note: Int resId doesn't work well with KMP resources. Let's assume you'd pass a resource path string.
    rawResName: String? = null,
    url: String? = null
) {
    // 1. Add a precondition to prevent crashes and enforce correct usage.
    // This ensures that exactly one source is provided.
    require(rawResName != null || url != null) { "LottieLoader requires either rawResName or url to be non-null" }
    require(rawResName == null || url == null) { "LottieLoader cannot accept both rawResName and url. Provide only one." }

    // 2. Determine the spec safely
    val spec = if (url != null) {
        LottieCompositionSpec.Url(url)
    } else {
        // In KMP, you load raw assets by their path string in the 'assets' folder.
        // E.g. "lottie/loader.json"
        LottieCompositionSpec.JsonString(rawResName!!)
    }

    val composition by rememberLottieComposition(spec)

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}