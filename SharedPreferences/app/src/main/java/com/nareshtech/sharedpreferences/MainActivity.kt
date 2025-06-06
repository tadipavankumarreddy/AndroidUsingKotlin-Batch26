package com.nareshtech.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nareshtech.sharedpreferences.ui.theme.SharedPreferencesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedPreferencesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SharedPreferencesScreen(context = this, padding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun SharedPreferencesScreen(context: MainActivity, padding: PaddingValues)
{
    // States for user Input
    var username by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    // states for retrieved data
    var savedUsername by remember { mutableStateOf("") }
    var savedAge by remember { mutableStateOf("") }

    // Shared Preferences instance (To Store and retrieve data)
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("userprefs", Context.MODE_PRIVATE)

    Column(modifier = Modifier.fillMaxSize()
        .padding(padding)) {

        TextField(value = username,
            onValueChange = {username = it},
            label = {Text("Enter the username")},
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        TextField(value = age,
            onValueChange = {age = it},
            label = {Text("Enter the Age")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                // Storing values into Shared Preferences
                sharedPreferences.edit().apply(){
                    putString("username", username)
                    putString("age", age)
                    apply()
                }

                username = ""
                age = ""
                Toast.makeText(context,"Data saved Successfully", Toast.LENGTH_LONG).show()
            },
                modifier = Modifier.weight(1f)) {
                Text("Save Data")
            }

            Button(onClick = {
                // Retrieve data
                savedUsername = sharedPreferences.getString("username", "")?:""
                savedAge = sharedPreferences.getString("age","")?:""
            },
                modifier = Modifier.weight(1f)) {
                Text("Retrieve Data")
            }
        }

        Text(text = "$savedUsername $savedAge", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

