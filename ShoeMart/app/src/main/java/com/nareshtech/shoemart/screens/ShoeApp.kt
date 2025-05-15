package com.nareshtech.shoemart.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nareshtech.shoemart.data.Shoe
import com.nareshtech.shoemart.data.ShoeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URL
import javax.net.ssl.HttpsURLConnection

@Composable
fun ShoeApp(viewModel: ShoeViewModel){
    val shoes = viewModel.shoe_list
    val isLoading = viewModel.isLoading
/*    // TODO 4: Download the JSON data
    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            // data fetching logic
            try{
                val url = URL("https://raw.githubusercontent.com/tadipavankumarreddy/AndroidUsingKotlin-Batch26/refs/heads/master/shoesdata.json")
                val connection = url.openConnection() as HttpsURLConnection
                val data = connection.inputStream.bufferedReader().readText()
                connection.disconnect()

                isLoading = false

                val result = Json { ignoreUnknownKeys = true}.decodeFromString<List<Shoe>>(data)
                shoeList = result
            }catch (e: Exception){
                e.printStackTrace()
                isLoading = false
            }
        }
    }*/

    // TODO 9.2 : Create NavController & NavHost with appropriate data
    val navController = rememberNavController()

    // TODO 7: Ctnd
    if(isLoading){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }else{
        NavHost(navController = navController, startDestination = "ShoeList"){
            composable("ShoeList"){
                ShoeGrid(shoes = viewModel.shoe_list){ selectedShoe ->
                    navController.navigate("ShoeDetails/${selectedShoe.id}")
                }
            }

            composable("ShoeDetails/{shoeId}") { backStackEntry ->
                val shoeId = backStackEntry.arguments?.getString("shoeId")?.toIntOrNull()
                val shoe = viewModel.getShoeById(shoeId)
                shoe?.let{
                    ShoeDetailsScreen(shoe = it)
                }
            }


        }
    }
}