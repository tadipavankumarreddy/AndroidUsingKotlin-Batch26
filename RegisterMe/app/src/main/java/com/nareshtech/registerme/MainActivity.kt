package com.nareshtech.registerme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nareshtech.registerme.ui.theme.RegisterMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterMeTheme {
                RegistrationScreen()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun RegistrationScreen() {
       var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var gender by remember { mutableStateOf("") }
        var languages = listOf("Tel","Eng","Hin","Tam")
        val selectedLangues = remember { mutableStateListOf<String>() }

        var scrollState = rememberScrollState()
        // New variable for storing the data
        var submittedData by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(16.dp)
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(WindowInsets.navigationBars.asPaddingValues())) {

            // Let's Give some heading to our Screen
            Text(text = "Register Me!", fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth().rotate(-10f).padding(10.dp)
            )

            // Creates an TextField to read name
            TextField(value = name, onValueChange = {name = it},
                label = { Text("Enter your name")},
                modifier = Modifier.fillMaxWidth())

            // Create an OutlinedTextField for reading the age
            OutlinedTextField(value = age, onValueChange = {age = it},
                label = { Text("Enter your Age")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth())

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {

                Text(text = "Gender")

                RadioButton(selected = gender=="Male",
                    onClick = {gender = "Male"})
                Text("Male")

                RadioButton(selected = gender=="Female",
                    onClick = {gender = "Female"})
                Text("Female")
            }

            // Let the user select multiple Languages
            Text("Languages Known", fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround){

                languages.forEach { language ->
                    Checkbox(checked = selectedLangues.contains(language), onCheckedChange = {
                        if(it) selectedLangues.add(language) else selectedLangues.remove(language)
                    })

                    Text(text = language, modifier = Modifier.weight(1f))
                }

            }

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                submittedData = "Name:$name\nAge:$age\nGender$gender\nLanguages:${selectedLangues.joinToString(",")}"
            }) {
                Text("Submit")
            }

            // Displaying submitted data
            if(submittedData.isNotEmpty()){
                Text(text = submittedData,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 20.dp).fillMaxWidth())
            }

        }
    }
}