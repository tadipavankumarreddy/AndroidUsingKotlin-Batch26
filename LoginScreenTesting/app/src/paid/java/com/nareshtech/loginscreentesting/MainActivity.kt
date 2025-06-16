package com.nareshtech.loginscreentesting

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nareshtech.loginscreentesting.ui.theme.LoginScreenTestingTheme

class MainActivity : ComponentActivity() {

    fun isValidCredentials(userName:String, password:String): Boolean{
        return userName == "admin" && password == "password123"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var loginSuccess by remember { mutableStateOf(false) }

            LoginScreenTestingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {

                        // Two OutlinedTextFields
                        OutlinedTextField(value = username, onValueChange = {username = it},
                            label = { Text("Enter User name") }, modifier = Modifier.fillMaxWidth())

                        OutlinedTextField(value = password, onValueChange = {password = it},
                            label = { Text("Enter password") }, modifier = Modifier.fillMaxWidth())

                        Button(onClick = {// implement it later
                            if(isValidCredentials(username, password)){
                                loginSuccess = true
                            }else{
                                loginSuccess = false
                            }
                        }) {
                            Text("Validate Sign in")
                        }

                        Text("Login Validation:${loginSuccess}")

                        Button(onClick = {}){
                            Text("Exclusive Paid Feature")
                        }
                    }
                }
            }
        }
    }
}
