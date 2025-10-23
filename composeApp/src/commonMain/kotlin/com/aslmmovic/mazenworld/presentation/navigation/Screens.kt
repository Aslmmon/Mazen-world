package com.aslmmovic.mazenworld.presentation.navigation

// presentation/navigation/Screen.kt
sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object CategoryMap : Screen("category_map")
}