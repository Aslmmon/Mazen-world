package com.aslmmovic.mazenworld.presentation.components

// commonMain/presentation/ui/game/OptionsGrid.kt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.data.model.GameOptionDto
import org.jetbrains.compose.resources.painterResource

@Composable
fun OptionsGrid(options: List<GameOptionDto>?, onOptionSelected: (String) -> Unit) {
    if (options.isNullOrEmpty()) return

    // Grid layout for 4 options (2 columns, 2 rows)
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {

        // Loop through pairs of options (Row 1: Options 0 & 1, Row 2: Options 2 & 3)
        options.forEach { option ->
            // Option Button
            Box(
                modifier = Modifier
                    .size(80.dp) // Fixed button size
                    .withPressAnimation(
                        onClick = { onOptionSelected(option.id) }
                    )
                    .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                // Content: Text or Image, depending on the game type
                if (option.iconUrl != null) {
                    // Render Image Option (e.g., ANIMALS/SHAPES)
//                    Image(
//                        painter = painterResource(option.iconUrl),
//                        contentDescription = option.text,
//                        modifier = Modifier.fillMaxSize(0.8f)
//                    )
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