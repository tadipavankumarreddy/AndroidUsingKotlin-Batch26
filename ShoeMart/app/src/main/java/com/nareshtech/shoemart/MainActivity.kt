package com.nareshtech.shoemart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import com.nareshtech.shoemart.data.ShoeViewModel
import com.nareshtech.shoemart.networking.isInternetAvailable
import com.nareshtech.shoemart.screens.ShoeApp
import com.nareshtech.shoemart.ui.theme.ShoeMartTheme

class MainActivity : ComponentActivity() {
    lateinit var viewModel: ShoeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoeMartTheme {
                viewModel = ViewModelProvider(this)[ShoeViewModel::class.java]
                // TODO 8.2 - If there is no network, show the user an alert dialog.
                var showDialog = remember { mutableStateOf(false) }
                if(!isInternetAvailable(applicationContext)){
                    showDialog.value = true
                }
                if(showDialog.value){
                   ShowAlertDialog(showDialog) { }
                }else{
                    ShoeApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun ShowAlertDialog(showDialog: MutableState<Boolean>, onDismiss: () -> Unit) {
    // Show an alert dialog box
    AlertDialog(
        confirmButton = {
            // TODO 8.3 - Dismiss the alert dialog box
            TextButton(onClick = {showDialog.value = false}) { Text("OK") }
        },
        onDismissRequest = {
            showDialog.value = false
        },
        title = { Text("No Internet Connection") },
        text = { Text("Please check your internet connection and try again.") }
    )
}


