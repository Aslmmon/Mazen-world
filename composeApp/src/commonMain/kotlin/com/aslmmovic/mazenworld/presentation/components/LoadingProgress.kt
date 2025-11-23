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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.presentation.ui.splash.LoadingStatus
import com.aslmmovic.mazenworld.utils.getLocalizedStatusText
import com.aslmmovic.mazenworld.utils.happyLoading
import com.aslmmovic.mazenworld.utils.loadingTiger
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.back_icon


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
//            LottieLoader(modifier = Modifier.fillMaxSize(0.7f), url = loadingTiger)
            LottieLoader(modifier = Modifier.fillMaxSize(0.7f), file = "cute_tiger.json")

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
fun ErrorComponent(errorMssage: String,onBackClick: () -> Unit = {}) {
    // Show a user-friendly error message in the center
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {

        SmallIconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(5.dp),
            contentDescription = "back",
            icon = Res.drawable.back_icon
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LottieLoader(modifier = Modifier.fillMaxSize(0.7f), file = "cute_tiger.json")
            Text(
                errorMssage, // "Getting Ready..."
                fontSize = 15.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )

        }

    }

}