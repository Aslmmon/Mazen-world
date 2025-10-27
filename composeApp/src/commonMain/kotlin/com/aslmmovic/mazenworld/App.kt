package com.aslmmovic.mazenworld

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aslmmovic.mazenworld.presentation.navigation.Screen
import com.aslmmovic.mazenworld.presentation.theme.MazenWorldTheme
import com.aslmmovic.mazenworld.presentation.ui.categories.CategoryMapScreen
import com.aslmmovic.mazenworld.presentation.ui.gameplay.GamePlayScreen
import com.aslmmovic.mazenworld.presentation.ui.home.HomeScreen
import com.aslmmovic.mazenworld.presentation.ui.splash.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    MazenWorldTheme {
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
            composable(
                // Route now expects an argument named 'categoryId'
                route = "${Screen.PlayScreen.route}/{categoryId}",
                arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString("categoryId")
                if (categoryId != null) {
                    GamePlayScreen(navController, categoryId)
                } else {
                    // Handle error case (should not happen if navigated correctly)
                    Text("Error: Category ID not found", color = Color.Red)
                }
            }
        }
    }
}