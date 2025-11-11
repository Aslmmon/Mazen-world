package com.aslmmovic.mazenworld.presentation.ui.gameplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aslmmovic.mazenworld.domain.util.toUserFriendlyMessage
import com.aslmmovic.mazenworld.presentation.components.ErrorComponent
import com.aslmmovic.mazenworld.presentation.components.GameProgressBar
import com.aslmmovic.mazenworld.presentation.components.LevelCompleteOverlay
import com.aslmmovic.mazenworld.presentation.components.LoadingProgress
import com.aslmmovic.mazenworld.presentation.components.OptionsGrid
import com.aslmmovic.mazenworld.presentation.components.QuestionArea
import com.aslmmovic.mazenworld.presentation.components.SmallIconButton
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.back_icon
import mazenworld.composeapp.generated.resources.board_frame
import mazenworld.composeapp.generated.resources.music_icon
import mazenworld.composeapp.generated.resources.play_screen_bg
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GamePlayScreen(onBackClick: () -> Unit, categoryId: String) {
    val viewModel: GameViewModel = koinViewModel(
        parameters = { parametersOf(categoryId) } // Pass the argument here
    )
    val state by viewModel.state.collectAsStateWithLifecycle()


    Box(modifier = Modifier.fillMaxSize()) {

        when (val currentState = state) {
            is GameState.Loading -> {
                LoadingProgress("raw/cute_tiger.json")
            }
            is GameState.Error -> {
                ErrorComponent(
                    currentState.message.toUserFriendlyMessage(),
                    onBackClick = onBackClick
                )
            }
            is GameState.Success -> {
                GameContent(
                    state = currentState,
                    onBackClick = onBackClick,
                    onProcessAnswer = viewModel::processAnswer
                )
                if (currentState.isLevelComplete) {
                    LevelCompleteOverlay(
                        onBackClick,
                        currentState.score,
                        currentState.totalQuestions
                    )
                }
            }

        }
    }
}

@Composable
private fun GameContent(
    state: GameState.Success,
    onBackClick: () -> Unit,
    onProcessAnswer: (String) -> Unit
) {
    // Top-left buttons (Back, Music)
    Image(
        painter = painterResource(Res.drawable.play_screen_bg),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SmallIconButton(
            onClick = onBackClick,
            contentDescription = "back",
            icon = Res.drawable.back_icon
        )
        SmallIconButton(
            onClick = {},
            contentDescription = "music",
            icon = Res.drawable.music_icon
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize() // Fill the screen to align center correctly
            .padding(horizontal = 100.dp, vertical = 32.dp), // Adjust padding for a good fit
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.board_frame),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GameProgressBar(state.currentQuestionIndex + 1, state.totalQuestions)
            QuestionArea(state.currentQuestion)
            OptionsGrid(state.currentQuestion.options, onProcessAnswer)

            state.feedbackMessage?.let { msg ->
                Text(
                    msg,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (msg == "Correct!") Color(0xFF2C6E49) else Color.Red
                )
            }
        }
    }
}

