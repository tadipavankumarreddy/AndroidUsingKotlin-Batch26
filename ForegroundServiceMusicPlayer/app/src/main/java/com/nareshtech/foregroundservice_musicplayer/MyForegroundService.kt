package com.nareshtech.foregroundservice_musicplayer

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

// TODO 1: Download an MP3 file and place it in res/raw folder
// TODO 2: Create a class that extends to Service class.

class MyForegroundService : Service() {

    private lateinit var player: MediaPlayer
    private lateinit var nm: NotificationManager

    // This is the mandatory method to implement when you create any type of a service.
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.audio)
        nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    // This method is called by the android system when a service is created and then to start it.
    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = showNotification()

        startForeground(1, notification)

        // Start playing music
        player.start()

        // if killed, restart with START_NOT_STICKY
        return START_NOT_STICKY
    }

    private fun showNotification(): Notification {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel("my_foreground", "My Foreground Service",
                NotificationManager.IMPORTANCE_HIGH)
            nm.createNotificationChannel(notificationChannel)
        }

        val notification =  NotificationCompat.Builder(this, "my_foreground")
            .setContentTitle("Playing Audio")
            .setContentText("Your song is playing in the background")
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        return notification
    }

    override fun onDestroy() {
        player.stop()
        player.release()
        super.onDestroy()
    }
}