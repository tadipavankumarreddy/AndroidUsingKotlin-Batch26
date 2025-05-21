package com.nareshtech.powerreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.nareshtech.powerreceiver.ui.theme.PowerReceiverTheme

// TODO 2: Create Two Images -> one Rep battery charging state, other rep battery discharging state

class MainActivity : ComponentActivity() {
    // TODO 3: Create a powerReceiver Obj
    private lateinit var powerReceiver: PowerReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PowerReceiverTheme {
                val context = LocalContext.current
                var isPowerConnected by remember { mutableStateOf(false) }

                /**
                 * DisposableEffect is a composable function just like our LaunchedEffect
                 * It lets you run side effects safely within composable lifecycle
                 * The `unit` parameter means that effect will run only once when the composables enters the composition and
                 * will clean up when it leaves.*/

                DisposableEffect(Unit) {
                    powerReceiver = PowerReceiver { connected ->
                        isPowerConnected = connected
                    }

                    // TODO 4: Register a Receiver
                    val powerFilter = IntentFilter().apply {
                        addAction(Intent.ACTION_POWER_CONNECTED)
                        addAction(Intent.ACTION_POWER_DISCONNECTED)
                        addAction("com.nareshtech.powerreceiver.CUSTOM_BROADCAST")
                    }

                    context.registerReceiver(powerReceiver,powerFilter)

                    // Actual Clean up of unregistration is happening here
                    onDispose {
                        context.unregisterReceiver(powerReceiver)
                    }
                }

                Column(
                    modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
                        .padding(WindowInsets.navigationBars.asPaddingValues())
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            if(isPowerConnected)
                                R.drawable.baseline_battery_charging_full_24
                            else
                                R.drawable.baseline_battery_alert_24
                        ),
                        contentDescription = "Power Rep Image",
                        modifier = Modifier.size(200.dp)
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(onClick = {
                        val intent = Intent("com.nareshtech.powerreceiver.CUSTOM_BROADCAST")
                        sendBroadcast(intent)
                    }, modifier = Modifier.fillMaxWidth().align(Alignment.End)) {
                        Text("Send Custom Broadcast")
                    }
                }
            }
        }
    }
}

