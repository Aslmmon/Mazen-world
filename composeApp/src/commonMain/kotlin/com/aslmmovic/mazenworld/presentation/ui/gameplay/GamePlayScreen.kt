package com.aslmmovic.mazenworld.presentation.ui.gameplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.presentation.components.GameProgressBar
import com.aslmmovic.mazenworld.presentation.components.OptionsGrid
import com.aslmmovic.mazenworld.presentation.components.QuestionArea
import com.aslmmovic.mazenworld.presentation.components.SmallIconButton
import com.aslmmovic.mazenworld.presentation.ui.home.HomeViewModel
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.back_icon
import mazenworld.composeapp.generated.resources.board_frame
import mazenworld.composeapp.generated.resources.music_icon
import mazenworld.composeapp.generated.resources.play_screen_bg
import mazenworld.composeapp.generated.resources.treesvg
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GamePlayScreen(onBackClick: () -> Unit, categoryId: String) {
    val viewModel: GameViewModel = koinViewModel(
        parameters = { parametersOf(categoryId) } // Pass the argument here
    )
    val homeViewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        // Background (e.g., farm background)
        Image(
            painter = painterResource(Res.drawable.play_screen_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Main content Box (board + game)
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.7f),
            contentAlignment = Alignment.Center
        ) {
            // Board frame as background
            Image(
                painter = painterResource(Res.drawable.board_frame),
                contentDescription = null,
                contentScale = ContentScale.FillBounds, // Use FillBounds to stretch the image as a background
                modifier = Modifier.fillMaxSize()
            )

            // Conditional content: game or level complete
            if (state.isLevelComplete) {
                LevelCompleteOverlay(onBackClick, state.score) // 🌟 Display completion screen
            } else {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GameProgressBar(state.currentQuestionIndex, state.totalQuestions)
                    QuestionArea(state.currentQuestion)
                    OptionsGrid(state.currentQuestion?.options, viewModel::processAnswer)

                    state.feedbackMessage?.let { msg ->
                        Text(
                            msg,
                            color = Color.Red.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }

        // Top-left buttons
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 10.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SmallIconButton(
                onClick = onBackClick,
                contentDescription = "back",
                icon = Res.drawable.back_icon
            )
            SmallIconButton(
                onClick = homeViewModel::toggleMusic,
                contentDescription = "music",
                icon = Res.drawable.music_icon
            )
        }
    }
}

@Composable
fun BoxScope.LevelCompleteOverlay(onBackClick: () -> Unit, score: Int) {
    // Semi-transparent overlay to draw attention
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .align(Alignment.Center),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Success Message
            Text(
                "المستوى اكتمل!",
                fontSize = 36.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text("لقد كسبت 25 نجمة!", fontSize = 24.sp, color = Color.Yellow)
            Text("نقاطك: $score/${5}", fontSize = 18.sp, color = Color.LightGray)

            // Button to return to the map
            SmallIconButton(
                onClick = onBackClick,
                contentDescription = "Return to Map",
                // Use a different icon/frame for the final button
                icon = Res.drawable.treesvg
            )
        }
    }
}
