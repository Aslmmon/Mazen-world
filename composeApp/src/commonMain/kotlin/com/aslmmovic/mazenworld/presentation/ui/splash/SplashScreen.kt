package com.aslmmovic.mazenworld.presentation.ui.splash

import CharacterMood
import YellowCharacter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.presentation.components.CustomRoundedProgressIndicator
import com.aslmmovic.mazenworld.utils.getLocalizedStatusText
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(onNavigateToHome: () -> Unit) {

    val viewModel: SplashViewModel = koinViewModel()
    val isLoaded by viewModel.isDataLoaded.collectAsState()
    val currentStatus by viewModel.loadingStatus.collectAsState()
    val currentLoadingText = getLocalizedStatusText(currentStatus)
    val progressValue by viewModel.progress.collectAsState()

    LaunchedEffect(isLoaded) {
        if (isLoaded) {
            onNavigateToHome()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            YellowCharacter(mood = CharacterMood.Thinking)
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