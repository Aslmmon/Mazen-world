package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.presentation.ui.splash.LoadingStatus
import com.aslmmovic.mazenworld.utils.getLocalizedStatusText
import com.aslmmovic.mazenworld.utils.loadingTiger


@Composable
fun LoadingProgress(rawLottieFile: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LottieLoader(modifier = Modifier.fillMaxSize(0.7f), url = loadingTiger)
            Text(
                getLocalizedStatusText(LoadingStatus.PREPARING), // "Getting Ready..."
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

    }

}

@Composable
fun ErrorComponent(errorMssage: String) {
    // Show a user-friendly error message in the center
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMssage,
            color = Color.Red,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }

}