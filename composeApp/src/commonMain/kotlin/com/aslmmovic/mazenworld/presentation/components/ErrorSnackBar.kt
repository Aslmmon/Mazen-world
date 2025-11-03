package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ErrorSnackbar(
    message: String,
    modifier: Modifier = Modifier
) {
    Snackbar(
        modifier = modifier
            .padding(16.dp)
            .background(
                color = Color.Red.copy(alpha = 0.8f),
                shape = RoundedCornerShape(8.dp)
            ),
        content = {
            Text(
                text = message,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        },
        containerColor = Color.Transparent, // The background is handled by the modifier
        contentColor = Color.Blue
    )
}