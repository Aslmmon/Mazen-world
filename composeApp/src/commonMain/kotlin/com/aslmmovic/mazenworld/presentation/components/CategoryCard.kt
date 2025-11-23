package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.data.model.CategoryDto
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.car_2
import org.jetbrains.compose.resources.painterResource

@Composable
fun CategoryCard(
    item: CategoryDto,
    onCategoryClick: (CategoryDto) -> Unit,
    modifier: Modifier = Modifier
) {


    Card(
        modifier = modifier
            .padding(8.dp)
            .height(170.dp)
            .clickable { onCategoryClick(item) },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            // Background with the curved design
            Box(modifier = Modifier.fillMaxSize()) {
                // Top blue part
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.45f)
                        .background(MaterialTheme.colorScheme.secondary)
                )
                // Bottom white part with a curved top edge
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp) // Adjust this to control the curve position
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        )
                )
            }

            // Content (Text and Image)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp, start = 4.dp, end = 4.dp)
                )

                if (item.isLocked) {
                    // Locked state UI
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.car_2),
                            contentDescription = "Locked",
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "${item.starCost}",
//                            fontSize = 22.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Gray
//                        )
                    }
                } else {
                    LottieLoader(modifier = Modifier.fillMaxSize(0.9f), file = "cute_tiger.json")

//                    LottieLoader(modifier = Modifier.fillMaxSize(0.9f), url = item.iconUrl)
                }
            }
        }
    }
}


