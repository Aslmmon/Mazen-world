package com.aslmmovic.mazenworld.presentation.navigation

import androidx.navigation.NavHostController

// A new helper class
class NavigationActions(private val navController: NavHostController) {
    fun navigateToHome() {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    fun navigateToCategoryMap() {
        navController.navigate(Screen.CategoryMap.route)
    }

    fun navigateToGamePlay(categoryId: String) {
        navController.navigate("${Screen.PlayScreen.route}/$categoryId")
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}

