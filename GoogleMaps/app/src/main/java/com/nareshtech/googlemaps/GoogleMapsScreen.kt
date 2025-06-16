package com.nareshtech.googlemaps

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

// TODO 5: Create google maps screen for showing the map and also showing the required location.

@Composable
fun GoogleMapsScreen() {
    var context = LocalContext.current
    val fusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var userLocation by remember {
        mutableStateOf<LatLng?>(null)
    }

    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(LatLng(17.36163,78.47467),12f)
    }

    // permission handling
    val hasPermission = remember {
        ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    LaunchedEffect(key1 = hasPermission) {
        if(hasPermission){
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                location ->
                location?.let { userLocation = LatLng(it.latitude, it.longitude)
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if(!hasPermission){
            Text(text = "Location Permission not granted",
                modifier = Modifier.padding(16.dp))
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = hasPermission)
        ){
            // Markers
            val charminar = LatLng(17.36163,78.47467)
            val golconda = LatLng(17.383056,78.401111)

            Marker(state = MarkerState(position = charminar), title = "Charminar")
            Marker(state = MarkerState(position = golconda), title = "Golconda")

            userLocation?.let {
                Marker(state = MarkerState(position = it), title = "My Location")
            }
        }
    }
}