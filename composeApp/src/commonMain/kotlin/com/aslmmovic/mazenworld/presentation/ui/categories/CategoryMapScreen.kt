package com.aslmmovic.mazenworld.presentation.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.presentation.components.CategoryCard
import com.aslmmovic.mazenworld.presentation.components.ErrorSnackbar
import com.aslmmovic.mazenworld.presentation.components.SmallIconButton
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.back_icon
import mazenworld.composeapp.generated.resources.category_map
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CategoryMapScreen(
    onBackClick: () -> Unit,
    onCategoryClick: (CategoryDto) -> Unit
) {
    val viewModel: CategoryMapViewModel = koinViewModel()
    val mapState by viewModel.mapState.collectAsStateWithLifecycle()

    // 1. Create a SnackbarHostState to control the snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // 2. Use LaunchedEffect to react to new messages from the ViewModel
    LaunchedEffect(viewModel.message) {
        viewModel.message.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                // Use our custom ErrorSnackbar
                ErrorSnackbar(message = it.visuals.message)
            }
        },
        containerColor = Color.Transparent // Make Scaffold background transparent
    ) { innerPadding -> // The content of the Scaffold

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(Res.drawable.category_map),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            SmallIconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                contentDescription = "back",
                icon = Res.drawable.back_icon
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4), // Or any number that fits your design
                modifier = Modifier.padding(top = 80.dp) // Adjust padding as needed
            ) {
                items(mapState.categories) { item ->
                    CategoryCard(
                        item = item,
                        onCategoryClick = onCategoryClick
                    )
                }
            }


        }

    }
}

