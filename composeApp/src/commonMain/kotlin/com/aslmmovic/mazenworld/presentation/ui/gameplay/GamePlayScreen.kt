package com.aslmmovic.mazenworld.presentation.ui.gameplay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun GamePlayScreen(navController: NavHostController, categoryId: String) {

    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            "Game Play Screen $categoryId",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(20.dp),
            color = Color.Black
        )

    }
}