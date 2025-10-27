package com.aslmmovic.mazenworld.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aslmmovic.mazenworld.presentation.components.CustomRoundedProgressIndicator
import com.aslmmovic.mazenworld.presentation.navigation.Screen
import com.aslmmovic.mazenworld.utils.getLocalizedStatusText
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.app_logo
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
@Composable
fun SplashScreen(navController: NavController) {

    val viewModel: SplashViewModel = koinViewModel()
    val isLoaded by viewModel.isDataLoaded.collectAsState()
    val currentStatus by viewModel.loadingStatus.collectAsState()
    val currentLoadingText = getLocalizedStatusText(currentStatus)
    val progressValue by viewModel.progress.collectAsState()

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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(Res.drawable.app_logo), // Replace with your logo resource
                contentDescription = "Mazen World Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(250.dp)
            )
            CustomRoundedProgressIndicator(
                progressValue = progressValue,
                trackOutlineColor = MaterialTheme.colorScheme.tertiary,
                progressColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = currentLoadingText,
                color = Color.DarkGray,
                fontSize = 22.sp
            )


        }
    }
}