package com.aslmmovic.mazenworld.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.aslmmovic.mazenworld.presentation.components.SmallIconButton
import com.aslmmovic.mazenworld.presentation.components.withPressAnimation
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.background
import mazenworld.composeapp.generated.resources.music_icon
import mazenworld.composeapp.generated.resources.parent_icon
import mazenworld.composeapp.generated.resources.play_icon
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(onPlayClick: () -> Unit) {
    val viewModel: HomeViewModel = koinViewModel()
    val profile = viewModel.profileState


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            // NOTE: Use the resource that corresponds to your home.png file.
            // Assuming you named the asset 'home' and it's in resources/drawable.
            painter = painterResource(Res.drawable.background),
            contentDescription = "Mazen World Background",
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
                onClick = viewModel::toggleMusic,
                contentDescription = "music",
                icon = Res.drawable.music_icon

            )
            SmallIconButton(
                onClick = {},
                contentDescription = "parent",
                icon = Res.drawable.parent_icon

            )
        }
        Image(
            // Assuming you have an asset named 'play_button' or similar
            painter = painterResource(Res.drawable.play_icon),
            contentDescription = "Play Game",
            contentScale = ContentScale.Fit,

            modifier = Modifier
                .size(180.dp) // Large size for easy tapping
                .align(Alignment.Center)
                .withPressAnimation(
                    onClick = onPlayClick
                    // Uses default "button_click" sound
                )

        )


    }

}
