package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.presentation.theme.mazenFontFamily
import com.aslmmovic.mazenworld.utils.ResourcesHelpers
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.background_overlay
import mazenworld.composeapp.generated.resources.car_2
import mazenworld.composeapp.generated.resources.rippon
import org.jetbrains.compose.resources.painterResource

@Composable
fun BoxScope.LevelCompleteOverlay(
    onRetryClick: () -> Unit,
    onNextClick: () -> Unit,
    score: Int,
    totalQuestions: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .align(Alignment.Center)
            .clickable(enabled = false) {}, // Block clicks
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            // Main Board
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(300.dp)
                    .padding(top = 30.dp) // Space for ribbon
            ) {
                Image(
                    painter = painterResource(Res.drawable.background_overlay),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Stars
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        val stars = calculateStars(score, totalQuestions)
                        repeat(3) { index ->
                            val isActive = index < stars
                            Icon(
                                painter = painterResource(Res.drawable.car_2),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(60.dp)
                                    .offset(y = if (index == 1) (-10).dp else 0.dp), // Middle star higher
                                tint = if (isActive) Color(0xFFFFD700) else Color.Gray.copy(alpha = 0.5f)
                            )
                        }
                    }

                    // Text
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Hurrahh!!! Added $score Star",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "For Correct Answer.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Black
                            ),
                            textAlign = TextAlign.Center
                        )
                    }

                    // Buttons
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.offset(y = 30.dp) // Push buttons down slightly to overlap edge
                    ) {
                        CircularButton(

                            icon = Icons.Rounded.Refresh,
                            onClick = onRetryClick,
                            color = Color(0xFF5D4037) // Brown
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        CircularButton(
                            icon = Icons.Rounded.ArrowForward,
                            onClick = onNextClick,
                            color = Color(0xFF3E2723) // Darker Brown
                        )
                    }
                }
            }

            // Ribbon (Overlaying the top)
            Box(
                modifier = Modifier
                    .width(280.dp)
                    .height(80.dp)
                    .offset(y = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.rippon),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = "Your Score",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = mazenFontFamily(), // Use custom font if available
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3E2723) // Dark Brown text
                    ),
                    modifier = Modifier.padding(bottom = 10.dp) // Adjust text position within ribbon
                )
            }
        }

        // Confetti Animation
        LottieLoader(
            modifier = Modifier.fillMaxSize(),
            file = ResourcesHelpers.CONFETTI_EFFECT
        )
    }
}

@Composable
fun CircularButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    color: Color
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(4.dp) // Border effect
            .clip(CircleShape)
            .background(color)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}

fun calculateStars(score: Int, total: Int): Int {
    if (total == 0) return 0
    val percentage = (score.toFloat() / total.toFloat()) * 100
    return when {
        percentage >= 80 -> 3
        percentage >= 50 -> 2
        else -> 1
    }
}
