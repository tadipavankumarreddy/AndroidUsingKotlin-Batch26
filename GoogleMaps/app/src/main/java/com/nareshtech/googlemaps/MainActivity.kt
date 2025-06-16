package com.nareshtech.googlemaps

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import com.nareshtech.googlemaps.ui.theme.GoogleMapsTheme

class MainActivity : ComponentActivity() {
    // TODO 4: Request the permissions that are very much needed
    companion object{
        private val REQUIRED_PERMISSIONS = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ).toTypedArray()
    }

    val activityResultLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        permissions ->
        var permissionsGranted = true
        permissions.entries.forEach {
            if(it.key in REQUIRED_PERMISSIONS && it.value == false){
                permissionsGranted = false

            }

            if(!permissionsGranted){
                Toast.makeText(this, "Location Permission is not granted", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            activityResultLauncher.launch(REQUIRED_PERMISSIONS)
            GoogleMapsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        // Will display GoogleMapScreen()
                        GoogleMapsScreen()
                    }
                }
            }
        }
    }
}
