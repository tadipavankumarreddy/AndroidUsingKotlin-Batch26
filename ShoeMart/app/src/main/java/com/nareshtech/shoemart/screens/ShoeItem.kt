package com.nareshtech.shoemart.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nareshtech.shoemart.R
import com.nareshtech.shoemart.data.Shoe

// TODO 5: Design one Shoe item for LazyVerticalGrid
@Composable
fun ShoeItem(shoe: Shoe){
    Column(modifier = Modifier.padding(8.dp)
        .graphicsLayer{
            shape = RoundedCornerShape(16.dp)
            clip = true
        }.background(Color.LightGray)
        .height(250.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Image(
            painter = rememberAsyncImagePainter(model = shoe.img,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.broken)),
            contentDescription = shoe.name,
            modifier = Modifier.height(150.dp).fillMaxWidth().padding(8.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(4.dp))

        ShoeText(shoe.name)
        ShoeText(shoe.price)
    }
}

@Composable
fun ShoeText(text:String){
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Black,
        modifier = Modifier.padding(8.dp)
    )
}