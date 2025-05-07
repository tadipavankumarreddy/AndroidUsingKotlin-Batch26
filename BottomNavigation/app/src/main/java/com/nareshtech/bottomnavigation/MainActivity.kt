package com.nareshtech.bottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.nareshtech.bottomnavigation.ui.theme.BottomNavigationTheme
import com.nareshtech.bottomnavigation.uiscreens.CartScreen
import com.nareshtech.bottomnavigation.uiscreens.HomeScreen
import com.nareshtech.bottomnavigation.uiscreens.ProfileScreen
import com.nareshtech.bottomnavigation.uiscreens.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationTheme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen(){
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
            val graph = navController.createGraph(startDestination = Screen.Home.route){
                composable(route = Screen.Cart.route) { CartScreen() }
                composable(route = Screen.Setting.route) { SettingsScreen() }
                composable(route = Screen.Home.route) { HomeScreen() }
                composable(route = Screen.Profile.route) { ProfileScreen() }
            }

        NavHost(navController = navController, graph = graph, modifier = Modifier.padding(innerPadding))
    }
}

