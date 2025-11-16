package com.aslmmovic.mazenworld.presentation.components

// commonMain/presentation/ui/game/OptionsGrid.kt

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aslmmovic.mazenworld.data.model.GameOptionDto

@Composable
fun OptionsGrid(options: List<GameOptionDto>?, onOptionSelected: (String) -> Unit) {
    if (options.isNullOrEmpty()) return

    Log.e("test options value", "OptionsGrid called with ${options} options")
    var selectedOptionId by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(key1 = options.firstOrNull()?.id) {
        selectedOptionId = null
    }

    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {

        options.forEach { option ->
            // Option Button
            val isSelected = selectedOptionId == option.id
            val stableOnClick = remember(option.id, onOptionSelected) {
                {
                    selectedOptionId = option.id
                    onOptionSelected(option.id)
                }
            }

            val borderColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFFFFD700) else Color.Black, // Gold when selected, black otherwise
                animationSpec = tween(durationMillis = 200),
                label = "borderColorAnimation"
            )

            Box(
                modifier = Modifier
                    .size(80.dp) // Fixed button size
                    .clickable(onClick = stableOnClick)
                    .border(
                        width = if (isSelected) 4.dp else 2.dp, // Thicker border when selected
                        color = borderColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                // Content: Text or Image, depending on the game type
                if (option.iconUrl != null) {
                    // Render Image Option (e.g., ANIMALS/SHAPES)
                    AsyncImage(
                        model = option.iconUrl,
                        contentDescription = option.text,
                        modifier = Modifier.fillMaxSize(0.8f)
                    )
                } else if (option.text != null) {
                    // Render Text Option (e.g., ALPHABET/VEHICLES)
                    Text(
                        option.text,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
        }


    }
}