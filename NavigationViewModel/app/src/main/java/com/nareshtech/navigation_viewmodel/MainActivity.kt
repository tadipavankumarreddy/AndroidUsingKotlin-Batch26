package com.nareshtech.navigation_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nareshtech.navigation_viewmodel.ui.theme.NavigationViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationViewModelTheme {
                val viewModel = ViewModelProvider(this).get(NCViewModel::class.java)
                AppNavigator(viewModel)
            }
        }
    }
}


@Composable
fun AppNavigator(viewModel: NCViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, viewModel) }
        composable("end") { EndScreen(viewModel) }
    }
}


@Composable
fun HomeScreen(navController: NavController, viewModel: NCViewModel){

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        OutlinedTextField(value = name,
            onValueChange = {name = it},
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Enter Name")})

        OutlinedTextField(value = age,
            onValueChange = {age = it},
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Enter Age")})

        Button(onClick = {
            viewModel.setName(name)
            viewModel.setAge(age)
            navController.navigate("end")
                         }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Submit")
        }
    }
}

@Composable
fun EndScreen(viewModel: NCViewModel){
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        
        Text("${viewModel.getName()} \n ${viewModel.getAge()}")
    }
}

class NCViewModel : ViewModel(){
    val name = mutableStateOf("")
    val age = mutableStateOf("")

    fun setName(n:String){
        name.value = n
    }

    fun setAge(a:String){
        age.value = a
    }

    fun getName():String{
        return name.value
    }

    fun getAge():String {
        return age.value
    }
}