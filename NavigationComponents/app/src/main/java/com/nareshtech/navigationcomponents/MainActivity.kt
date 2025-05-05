package com.nareshtech.navigationcomponents

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nareshtech.navigationcomponents.ui.theme.NavigationComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationComponentsTheme {
                AppNavigator()
            }
        }
    }
}

/**
 * The app should have four screens namely
 * - home (start destination) - This is shown by default to the users
 * - second
 * - third
 * - fourth
 * */

// TODO 1: To add Dependencies for Navigation Components (build.gradle (app))
// TODO 2: Building the home screen (First Screen that the user sees)


@Composable
fun HomeScreen(navController: NavController){
    /** This is screen 1
     * Three items in this screen
     *  - Text
     *  - Button
     *  - Button*/

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Home Screen")

        Button(onClick = { navController.navigate("second") }){
            Text("Go To Second Screen")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("third") }){
            Text("Go To Third Screen")
        }

    }

}

@Composable
fun SecondScreen(navController: NavController){
    /**
     * Components
     *  - Text
     *  - Button
     *  */
    val message = "Hello from Second Screen"

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Second Screen")

        Button(onClick = { navController.navigate("fourth/second/$message")}) {
            Text(" Go To 4th screen with message")
        }
    }
}


@Composable
fun ThirdScreen(navController: NavController){
    /**
     * Components
     *  - Text
     *  - Button
     *  */
    val message = "Data from Third Screen"

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Third Screen")

        Button(onClick = { navController.navigate("fourth/third/$message")}) {
            Text(" Go To 4th screen with message")
        }
    }
}


@Composable
fun FourthScreen(source:String, message: String, navController: NavController){

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Fourth Screen")
        Spacer(Modifier.height(20.dp))
        Text("Source:$source")
        Text("Message:$message")

        Button(onClick = { navController.navigate("home")}) {
            Text("Go to Home")
        }
    }

}

// Inorder to control the navigation, Let us create a navigator function

@Preview(showBackground = true)
@Composable
fun AppNavigator(){
    // Step 1 : We need a Navigation Controller - This helps in navigating between destinations
    val navController = rememberNavController()

    // Step 2: Wee need a NavHost to host all our destinations
    NavHost(navController = navController, startDestination = "home"){
        composable("home") { HomeScreen(navController) }
        composable("second") { SecondScreen(navController) }
        composable("third") { ThirdScreen(navController) }
        composable(route = "fourth/{source}/{message}",
            arguments = listOf(
                navArgument("source"){type = NavType.StringType},
                navArgument("message"){type = NavType.StringType})) { backStackEntry ->
            val source = backStackEntry.arguments?.getString("source")?:"unknown"
            val message = backStackEntry.arguments?.getString("message")?:"No Message"
            FourthScreen(source, message ,navController)
        }
    }
}