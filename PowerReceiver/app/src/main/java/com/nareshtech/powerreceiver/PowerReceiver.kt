package com.nareshtech.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PowerReceiver(private val onStateChanged:(Boolean) -> Unit) : BroadcastReceiver() {

    // TODO 1: Create a PowerReceiver to Receive ACTION_POWER_CONNECTED & ACTION_POWER_DISCONNECTED broadcasts
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            Intent.ACTION_POWER_CONNECTED -> onStateChanged(true)
            Intent.ACTION_POWER_DISCONNECTED -> onStateChanged(false)
            "com.nareshtech.powerreceiver.CUSTOM_BROADCAST" -> {
                Toast.makeText(context,"Received a custom broadcast", Toast.LENGTH_LONG).show()
            }
        }
    }
}