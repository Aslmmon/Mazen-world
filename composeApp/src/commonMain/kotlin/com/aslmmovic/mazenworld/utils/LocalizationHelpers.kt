package com.aslmmovic.mazenworld.utils

// commonMain/utils/LocalizationHelpers.kt

import androidx.compose.runtime.Composable
import com.aslmmovic.mazenworld.presentation.ui.splash.LoadingStatus
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.status_almost_ready
import mazenworld.composeapp.generated.resources.status_complete
import mazenworld.composeapp.generated.resources.status_initializing
import mazenworld.composeapp.generated.resources.status_loading_assets
import mazenworld.composeapp.generated.resources.status_preparing
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

// NOTE: This file assumes you have defined the actual string resources
// (status_preparing, status_loading_assets, etc.) in your strings.xml file.

/**
 * Maps the LoadingStatus enum to the correct StringResource ID.
 * This is the crucial helper for type-safe localization.
 */
fun getStringResourceForStatus(status: LoadingStatus): StringResource {
    return when (status) {
        LoadingStatus.INITIALIZING -> Res.string.status_initializing // e.g. "Initializing..."
        LoadingStatus.PREPARING -> Res.string.status_preparing
        LoadingStatus.LOADING_ASSETS -> Res.string.status_loading_assets
        LoadingStatus.ALMOST_READY -> Res.string.status_almost_ready
        LoadingStatus.COMPLETE -> Res.string.status_complete
    }
}

/**
 * Composable helper to fetch and display the translated string for the current status.
 */
@Composable
fun getLocalizedStatusText(status: LoadingStatus): String {
    val stringResource = getStringResourceForStatus(status)
    return stringResource(stringResource)
}

const val loadingBetweenScreensDelay = 5000L
const val loadingTiger = "https://raw.githubusercontent.com/Aslmmon/Mazen-world-assets/main/loading_assets/cute_tiger.json"
const val happyLoading = "https://raw.githubusercontent.com/Aslmmon/Mazen-world-assets/main/loading_assets/happy_loader.json"