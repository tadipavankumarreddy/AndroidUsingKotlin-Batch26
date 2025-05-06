package com.nareshtech.phoneorderingappjnc

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nareshtech.phoneorderingappjnc.ui.theme.PhoneOrderingAppJNCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhoneOrderingAppJNCTheme {
                val viewModel: PhoneOrderViewModel = ViewModelProvider(this).get(PhoneOrderViewModel::class.java)
                 PhoneOrderingApp(viewModel)
            }
        }
    }
}

@Composable
fun PhoneOrderingApp(viewModel: PhoneOrderViewModel = viewModel()){
    // Set up the navigationController
    val navController = rememberNavController()

    // Set up the Nav Host
    NavHost(navController = navController, startDestination = "home"){
        composable("home") { HomeScreen(navController, viewModel) }
        composable("variant") { VariantScreen(navController,viewModel) }

        composable("summary") {
            SummaryScreen(viewModel)
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController, viewModel: PhoneOrderViewModel) {

    val brands = listOf("Oppo","Samsung","Vivo","Nothing", "Motorola")
    var selectedBrand by remember { mutableStateOf(brands[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Select a Brand")

        brands.forEach { brand ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = brand == selectedBrand,
                    onClick = { selectedBrand = brand }
                )
                Text(text = brand)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.setBrand(selectedBrand)
            navController.navigate("variant")
        }) {
            Text("Next")
        }
    }

}

@Composable
fun VariantScreen(navController: NavHostController, viewModel: PhoneOrderViewModel) {
    val variants = listOf("8GB|128GB", "8GB|256GB", "8GB|512GB", "12GB|256GB", "12GB|512GB")
    var selectedVariant by remember { mutableStateOf(variants[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Brand: ${viewModel.getBrand()} ", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Select a Variant")
        variants.forEach { variant ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = variant == selectedVariant,
                    onClick = { selectedVariant = variant }
                )
                Text(text = variant)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.setVariant(selectedVariant)
            navController.navigate("summary")
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Next")
        }
    }

}

@Composable
fun SummaryScreen(viewModel: PhoneOrderViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your selection", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Brand: ${viewModel.getBrand()} ", style = MaterialTheme.typography.bodyLarge)
        Text("Variant: ${viewModel.getVariant()} ", style = MaterialTheme.typography.bodyLarge)
    }
}

class PhoneOrderViewModel : ViewModel(){
    private val selectedBrand = mutableStateOf("")
    private val selectedVariant = mutableStateOf("")

    fun setBrand(brand: String){
        selectedBrand.value = brand
    }

    fun getBrand():String{
        return selectedBrand.value
    }

    fun setVariant(variant: String){
        selectedVariant.value = variant

    }

    fun getVariant():String{
        return selectedVariant.value
    }

    fun clearSelection(){
        selectedBrand.value = ""
        selectedVariant.value = ""
    }
}