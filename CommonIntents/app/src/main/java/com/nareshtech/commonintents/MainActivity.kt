package com.nareshtech.commonintents

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_WIRELESS_SETTINGS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import com.nareshtech.commonintents.ui.theme.CommonIntentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CommonIntentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MyScreenDesign(applicationContext,innerPadding)
                }
            }
        }
    }
}

@Composable
fun MyScreenDesign(applicationContext: Context, innerPadding: PaddingValues) {
    var web_address by remember { mutableStateOf("") }
    var maps_address by remember { mutableStateOf("") }
    var phone_number by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(value = web_address,
            onValueChange = {web_address = it},
            label = { Text("Enter a Valid Web address")},
            modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://$web_address")
            i.flags = FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(i)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Take me to browser")
        }

        OutlinedTextField(value = maps_address,
            onValueChange = {maps_address = it},
            label = { Text("Enter a Valid Maps address")},
            modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("geo:0,0?q=$maps_address")
            i.flags = FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(i)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Take me to Maps")
        }

        OutlinedTextField(value = phone_number,
            onValueChange = {phone_number = it},
            label = { Text("Enter a Valid Phone Number")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            val i = Intent()
            i.action = Intent.ACTION_DIAL
            i.data = Uri.parse("tel:$phone_number")
            i.flags = FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(i)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Take me to Dailer")
        }

        Button(onClick = {
            val i = Intent()
            i.action = ACTION_WIRELESS_SETTINGS
            i.flags = FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(i)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Take me to Settings")
        }
    }
}
