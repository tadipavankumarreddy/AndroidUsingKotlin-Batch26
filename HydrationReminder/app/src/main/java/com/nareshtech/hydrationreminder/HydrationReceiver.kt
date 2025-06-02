package com.nareshtech.hydrationreminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

// TODO 3: This receiver is invoked as soon as an alarm is received.
class HydrationReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // Remind the users to have a glass of water
        val notificaitonManager: NotificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel("Hydration","Hydration Remainder", NotificationManager.IMPORTANCE_HIGH)
            notificaitonManager.createNotificationChannel(notificationChannel)
        }

        val notification = NotificationCompat.Builder(context, "Hydration")
            .setSmallIcon(R.drawable.outline_glass_cup_24)
            .setContentTitle("Hydration is needed")
            .setContentText("Drink 200ml of water now and maintain enough hydration")
            .setAutoCancel(true)
            .build()

        notificaitonManager.notify(23,notification)
    }
}