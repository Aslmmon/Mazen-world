package com.aslmmovic.mazenworld.presentation.navigation

// presentation/navigation/Screen.kt
sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object CategoryMap : Screen("category_map") // The first destination for the 'Play' button
    data object ParentSettings : Screen("parent_settings")
    data object RewardsCollection : Screen("rewards_collection")
}