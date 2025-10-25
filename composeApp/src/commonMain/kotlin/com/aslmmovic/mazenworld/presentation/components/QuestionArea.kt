package com.aslmmovic.mazenworld.presentation.components

// commonMain/presentation/ui/game/QuestionArea.kt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.domain.GameQuestion
import org.jetbrains.compose.resources.painterResource

@Composable
fun QuestionArea(question: GameQuestion?) {
    val defaultText = "Loading Question..."

    // Ensure we have a question before rendering
    if (question == null) {
        Text(defaultText, fontSize = 24.sp, color = Color.Gray)
        return
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

        // Question Text (e.g., "Find the Big Car" in Arabic)
        Text(
            text = question.questionText,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(Modifier.height(16.dp))

        // Question Image/Icon (e.g., the large Car picture)
        question.questionImageId?.let { resId ->
            Image(
                painter = painterResource(resId),
                contentDescription = "Question Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(25.dp) // Large visual prompt
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}