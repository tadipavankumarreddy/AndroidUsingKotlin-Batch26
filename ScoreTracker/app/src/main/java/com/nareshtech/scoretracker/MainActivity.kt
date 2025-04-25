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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nareshtech.scoretracker.ui.theme.ScoreTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenerateScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GenerateScreen(){
        Column(modifier = Modifier.fillMaxSize()) {
            Button(onClick = {/*TODO 1: Implement click later*/}, modifier = Modifier.fillMaxWidth().weight(1f)) {
                Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Box(modifier = Modifier.fillMaxWidth()
                .background(Color.Yellow)
                .weight(8f)
                .border(BorderStroke(5.dp, Color.Black)),
                contentAlignment = Alignment.Center){

                Text("0",
                    fontSize = 100.sp, fontWeight = FontWeight.Bold
                )
            }

            Button(onClick = {/*TODO 2: Implement click later*/}, modifier = Modifier.fillMaxWidth().weight(1f)) {
                Text("-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}