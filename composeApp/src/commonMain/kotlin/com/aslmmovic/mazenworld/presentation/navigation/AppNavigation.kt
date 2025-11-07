package com.aslmmovic.mazenworld.presentation.navigation

import IronManCharacter
import YellowCharacter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aslmmovic.mazenworld.presentation.ui.categories.CategoryMapScreen
import com.aslmmovic.mazenworld.presentation.ui.gameplay.GamePlayScreen
import com.aslmmovic.mazenworld.presentation.ui.home.HomeScreen
import com.aslmmovic.mazenworld.presentation.ui.splash.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController, navigationAction: NavigationActions) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(onNavigateToHome = navigationAction::navigateToHome)
        }
//        composable(Screen.Test.route) {
//            Column(
//
//            ) {
//
//                YellowCharacter(mood = CharacterMood.Thinking)
//                YellowCharacter(mood = CharacterMood.Right)
//                YellowCharacter(mood = CharacterMood.Wrong)
//
//            }
//        }
        composable(Screen.Home.route) {
            HomeScreen(
                onPlayClick = navigationAction::navigateToCategoryMap
            )
        }

        composable(Screen.CategoryMap.route) {
            CategoryMapScreen(
                onBackClick = navigationAction::navigateBack,
                onCategoryClick = { it ->
                    if (it.isLocked) {
//                        viewModel.attemptUnlock(it)
                    } else {
                        navigationAction.navigateToGamePlay(it.id.toString())
                    }
                })
        }
        composable(
            route = "${Screen.PlayScreen.route}/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            if (categoryId != null) {
                GamePlayScreen(
                    onBackClick = navigationAction::navigateBack,
                    categoryId = categoryId
                )
            } else {
                Text("Error: Category ID not found", color = Color.Red)
            }
        }
    }
}
