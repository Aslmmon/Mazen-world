package com.aslmmovic.mazenworld

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aslmmovic.mazenworld.presentation.navigation.Screen
import com.aslmmovic.mazenworld.presentation.ui.categories.CategoryMapScreen
import com.aslmmovic.mazenworld.presentation.ui.home.HomeScreen
import com.aslmmovic.mazenworld.presentation.ui.splash.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    MaterialTheme {
        NavHost(navController = navController, startDestination = Screen.Splash.route) {
            composable(Screen.Splash.route) {
                SplashScreen(navController)
            }
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(Screen.CategoryMap.route) {
                CategoryMapScreen(navController)
            }
        }
    }
}