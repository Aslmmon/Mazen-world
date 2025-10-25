package com.aslmmovic.mazenworld.presentation.ui.gameplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aslmmovic.mazenworld.presentation.components.GameProgressBar
import com.aslmmovic.mazenworld.presentation.components.OptionsGrid
import com.aslmmovic.mazenworld.presentation.components.QuestionArea
import com.aslmmovic.mazenworld.presentation.components.SmallIconButton
import com.aslmmovic.mazenworld.presentation.ui.home.HomeViewModel
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.back_icon
import mazenworld.composeapp.generated.resources.homebg
import mazenworld.composeapp.generated.resources.music_icon
import mazenworld.composeapp.generated.resources.play_screen_bg
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GamePlayScreen(navController: NavHostController, categoryId: String) {
    val viewModel: GameViewModel = koinViewModel(
        parameters = { parametersOf(categoryId) } // Pass the argument here
    )
    val homeViewModel: HomeViewModel = koinViewModel()


    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        // Background (e.g., farm background)
        Image(
            painterResource(Res.drawable.play_screen_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )



        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 32.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            SmallIconButton(
                onClick = {
                    navController.popBackStack()
                },
                contentDescription = "back",
                icon = Res.drawable.back_icon
            )

            SmallIconButton(
                onClick = homeViewModel::toggleMusic,
                contentDescription = "music",
                icon = Res.drawable.music_icon

            )

        }


        // Game Card (White Card in the center)
//        Column(
//            modifier = Modifier
//                .align(Alignment.Center)
//                .fillMaxWidth(0.9f)
//                .background(Color.White, shape = RoundedCornerShape(16.dp))
//                .padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Progress Bar (e.g., 30 questions)
//            GameProgressBar(state.currentQuestionIndex, state.totalQuestions)
//
//            Spacer(Modifier.height(24.dp))
//
//            // Question Area
//            QuestionArea(state.currentQuestion)
//
//            Spacer(Modifier.height(32.dp))
//
//            // Options Grid
//            OptionsGrid(state.currentQuestion?.options, viewModel::processAnswer)
//
//            // Feedback Text
//            state.feedbackMessage?.let { msg -> Text(msg, color = Color.Red.copy(alpha = 0.8f)) }
//        }
    }
}