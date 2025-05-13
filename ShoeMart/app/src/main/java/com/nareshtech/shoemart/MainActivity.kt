package com.nareshtech.shoemart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.nareshtech.shoemart.ui.theme.ShoeMartTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoeMartTheme {
                ShoeApp()
            }
        }
    }
}

@Composable
fun ShoeApp(){
    val scope = rememberCoroutineScope()
    var shoeList by remember { mutableStateOf<List<Shoe>>(emptyList()) }

    // TODO 4: Download the JSON data
    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            // data fetching logic
            try{
                val url = URL("https://raw.githubusercontent.com/tadipavankumarreddy/AndroidUsingKotlin-Batch26/refs/heads/master/shoesdata.json")
                val connection = url.openConnection() as HttpsURLConnection
                val data = connection.inputStream.bufferedReader().readText()
                connection.disconnect()

                val result = Json { ignoreUnknownKeys = true}.decodeFromString<List<Shoe>>(data)
                shoeList = result
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
// TODO 6: Create a LazyVerticalGrid
@Composable
fun ShoeGrid(shoes: List<Shoe>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(shoes) { shoe->
            ShoeItem(shoe)
        }
    }
}
// TODO 5: Design one Shoe item for LazyVerticalGrid
@Composable
fun ShoeItem(shoe: Shoe){
    Column(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberAsyncImagePainter(shoe.img),
            contentDescription = shoe.name,
            modifier = Modifier.fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Nike", style = MaterialTheme.typography.bodyLarge)
    }
}


