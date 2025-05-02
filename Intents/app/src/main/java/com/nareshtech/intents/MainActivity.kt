package com.nareshtech.intents

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
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
import com.nareshtech.intents.ui.theme.IntentsTheme

class MainActivity : ComponentActivity() {

    val launcher  = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result:ActivityResult->
        val data = result.data?.getStringExtra("key")
        Toast.makeText(applicationContext,data,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize().padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {

                        var name by remember { mutableStateOf("") }

                        OutlinedTextField(value = name, onValueChange = {name = it},
                            label = { Text(text = "Enter your name")},
                            modifier = Modifier.fillMaxWidth())

                        Button(onClick = {
                            // TODO: We shall write code using explicit intents and navigate to a new Activity.
                            val i = Intent(applicationContext,SecondActivity::class.java)
                            i.putExtra("KEY",name)
                            /*startActivity(i)*/
                            if(name.isNotEmpty()){
                                launcher.launch(i)
                            }
                        },
                            modifier = Modifier.padding(innerPadding).fillMaxWidth()) {
                            Text(text = "Click me!")
                        }

                    }
                }
            }
        }
    }
}
