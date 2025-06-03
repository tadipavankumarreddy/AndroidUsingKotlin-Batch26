package com.nareshtech.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.nareshtech.workmanager.ui.theme.WorkManagerTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // TODO 2: Create the conditions / Constraints for the work
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        // TODO 3: Create a work request
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<MyWorker>(5, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()


        setContent {
            WorkManagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {

                        OutlinedButton(onClick = {
                            WorkManager.getInstance(applicationContext).enqueue(workRequest)
                        }) {
                            Text("Enqueue my Work")
                        }

                        OutlinedButton(onClick = {
                            WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
                        }) {
                            Text("Enqueue my periodic work")
                        }
                    }

                }
            }
        }
    }
}
