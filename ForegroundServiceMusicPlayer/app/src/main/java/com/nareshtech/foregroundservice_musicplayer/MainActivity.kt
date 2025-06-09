package com.nareshtech.foregroundservice_musicplayer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nareshtech.foregroundservice_musicplayer.ui.theme.ForegroundServiceMusicPlayerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ForegroundServiceMusicPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

                        Button(onClick = {
                            val i = Intent(applicationContext, MyForegroundService::class.java)
                            startForegroundService(i)
                        }) {
                            Text("Play Music")
                        }

                        Button(onClick = {
                            val i = Intent(applicationContext, MyForegroundService::class.java)
                            stopService(i)
                        }) {
                            Text("Stop Music")
                        }
                    }
                }
            }
        }
    }
}