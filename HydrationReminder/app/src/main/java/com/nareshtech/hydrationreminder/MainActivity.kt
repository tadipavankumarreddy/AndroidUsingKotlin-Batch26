package com.nareshtech.hydrationreminder

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nareshtech.hydrationreminder.ui.theme.HydrationReminderTheme

class MainActivity : ComponentActivity() {
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent

    @SuppressLint("ScheduleExactAlarm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            pendingIntent = PendingIntent.getBroadcast(this, 12, Intent(this, HydrationReceiver::class.java),
                PendingIntent.FLAG_IMMUTABLE)

            HydrationReminderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize().padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        Button(onClick = {
                            // Todo 1: Set up the alarm - Enable the alarm
                            val alarmType = AlarmManager.ELAPSED_REALTIME
                            val triggerTime: Long = SystemClock.elapsedRealtime() // This is the time calculated since the system boot time in millis
                            val intervalTime : Long = 2*60*1000
                            alarmManager.setExact(alarmType,triggerTime,pendingIntent)
                        }) {
                            Text("Set Alarm")
                        }

                        Button(onClick = {
                            // Todo 2: Cancel the alarm
                            alarmManager.cancel(pendingIntent)
                        }) {
                            Text("Cancel Alarm")
                        }
                    }
                }
            }
        }
    }
}

