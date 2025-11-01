package com.aslmmovic.mazenworld.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslmmovic.mazenworld.domain.CategoryItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun CategoryCard(
    item: CategoryItem,
    onCategoryClick: (CategoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onCategoryClick(item) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (item.isLocked) {
                // You can replace this with a lock icon
                Text("Locked", fontSize = 24.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Cost: ${item.starCost} stars", fontSize = 16.sp, color = Color.Gray)
            } else {
                Image(
                    painter = painterResource(item.iconResource),
                    contentDescription = item.title,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(item.title, fontSize = 20.sp)
            }
        }
    }
}