package com.nareshtech.scoretracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nareshtech.scoretracker.ui.theme.ScoreTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val countViewModel:CountViewModel = ViewModelProvider(this).get(CountViewModel::class.java)
            GenerateScreen(countViewModel)
        }
    }

    @Composable
    fun GenerateScreen(countViewModel: CountViewModel){

        Column(modifier = Modifier.fillMaxSize()) {
            Button(onClick = {countViewModel.increment()}, modifier = Modifier.fillMaxWidth().weight(1f)) {
                Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Box(modifier = Modifier.fillMaxWidth()
                .background(Color.Yellow)
                .weight(8f)
                .border(BorderStroke(5.dp, Color.Black)),
                contentAlignment = Alignment.Center){

                Text(text = countViewModel.count.toString(),
                    fontSize = 100.sp, fontWeight = FontWeight.Bold
                )
            }

            Button(onClick = {countViewModel.decrement()}, modifier = Modifier.fillMaxWidth().weight(1f)) {
                Text("-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

class CountViewModel:ViewModel(){
    var count by mutableStateOf(0)

    fun increment(){
        count++
    }

    fun decrement(){
        count--
    }
}