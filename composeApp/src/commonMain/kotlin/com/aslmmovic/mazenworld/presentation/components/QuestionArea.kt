package com.aslmmovic.mazenworld.presentation.components

// commonMain/presentation/ui/game/QuestionArea.kt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aslmmovic.mazenworld.data.model.GameQuestionDto

@Composable
fun QuestionArea(question: GameQuestionDto?) {
    val defaultText = "Loading Question..."

    // Ensure we have a question before rendering
    if (question == null) {
        Text(defaultText, fontSize = 24.sp, color = Color.Gray)
        return
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        // Question Text (e.g., "Find the Big Car" in Arabic)
        Text(
            text = question.questionText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )


        // Question Image/Icon (e.g., the large Car picture)
        question.questionImageUrl.let { resId ->
//
            AsyncImage(
                model = resId,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(0.4f)
            )

        }
    }
}
