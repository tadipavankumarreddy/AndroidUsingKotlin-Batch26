package com.nareshtech.notetakingapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.compose.material3.Button
import androidx.compose.ui.text.Paragraph
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

const val CHANNEL_ID = "notes_channel_id"

fun createNotificationChannel(context: Context){
    // Create a Notification Channel
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        // Because Notification Channels are introduced from Android 8.0
        val name = "Notes Notification"
        val descriptionText = "Notification when a new note is added"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID,name,importance).apply { description = descriptionText }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}

@SuppressLint("MissingPermission")
fun showNoteAddedNotification(context: Context, noteTitle:String){
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent = PendingIntent.getActivity(context,98,intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

    // Convert the image into a bitmap
    val bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.noti_image)

    val builder = NotificationCompat.Builder(context,CHANNEL_ID)
        .setSmallIcon(R.drawable.outline_add_notes_24)
        .setContentTitle("New Note Added")
        .setContentText(noteTitle)
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .addAction(R.drawable.outline_add_notes_24,"Mark as Read",pendingIntent)
        .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))

    with(NotificationManagerCompat.from(context)) {
        notify(42, builder.build())
    }
}