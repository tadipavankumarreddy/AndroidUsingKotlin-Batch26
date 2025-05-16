package com.nareshtech.shoemart.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.nareshtech.shoemart.data.Shoe

@Composable
fun ShoeDetailsScreen(shoe: Shoe){

    var selectedSize by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize()
        .padding(16.dp)
        .padding(WindowInsets.statusBars.asPaddingValues())
        .padding(WindowInsets.navigationBars.asPaddingValues())
        .verticalScroll(rememberScrollState())) {
        Image(
            painter = rememberAsyncImagePainter(shoe.img),
            contentDescription = "My SHoe",
            modifier = Modifier.fillMaxWidth().height(250.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = shoe.name, style = MaterialTheme.typography.titleLarge)
        Text(text = "₹${shoe.price}", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Available Sizes:", fontWeight = FontWeight.Black, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.horizontalScroll(rememberScrollState())){
            shoe.sizes.forEach {size ->
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(if(size == selectedSize) Color.Red else Color.LightGray)
                    .clickable {selectedSize = size}
                    .padding(12.dp)){
                    Text(text = size, fontWeight = FontWeight.Black)
                }
            }
        }

    }
}