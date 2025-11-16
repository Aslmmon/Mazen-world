package com.aslmmovic.mazenworld

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.aslmmovic.mazenworld.presentation.navigation.AppNavigation
import com.aslmmovic.mazenworld.presentation.navigation.NavigationActions
import com.aslmmovic.mazenworld.presentation.theme.MazenWorldTheme
import com.aslmmovic.mazenworld.utils.provideAudioPlayerManager
import mazenworld.composeapp.generated.resources.Res
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {



    val navController = rememberNavController()
    val navigator = remember(navController) {
        NavigationActions(
            navController
        )
    }
    MazenWorldTheme {
        AppNavigation(navController, navigator)
    }
}
