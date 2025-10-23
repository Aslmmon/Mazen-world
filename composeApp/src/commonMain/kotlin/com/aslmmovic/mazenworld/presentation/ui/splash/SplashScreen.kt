package com.aslmmovic.mazenworld.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aslmmovic.mazenworld.presentation.navigation.Screen
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.treesvg
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

// presentation/ui/SplashScreen.kt
@Composable
fun SplashScreen(navController: NavController) {

    val viewModel: SplashViewModel = koinViewModel()
    val isLoaded by viewModel.isDataLoaded.collectAsState()


    LaunchedEffect(isLoaded) {
        if (isLoaded) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Use a Column to stack the Logo and the Indicator vertically
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // 1. Logo
            Image(
                painter = painterResource(Res.drawable.treesvg), // Replace with your logo resource
                contentDescription = "Mazen World Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(250.dp)
            )

            // Add some vertical space between logo and indicator
            Spacer(modifier = Modifier.height(32.dp))

            // 2. Circular Progress Indicator (The Loading Bar)
            CircularProgressIndicator(
                modifier = Modifier.size(40.dp),
                // Use colors matching your app's theme (e.g., a bright yellow or green)
                color = Color(0xFFFFCC00), // Bright Yellow
                strokeWidth = 4.dp
            )
        }
    }
}