package com.nareshtech.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
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
import com.nareshtech.jobscheduler.ui.theme.JobSchedulerTheme

class MainActivity : ComponentActivity() {

    lateinit var jobScheduler: JobScheduler
    lateinit var jobInfo: JobInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        jobInfo = JobInfo.Builder(45, ComponentName(packageName, MyJobService::class.java.name))
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setRequiresCharging(true)
            .build()

        setContent {
            JobSchedulerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {

                        OutlinedButton(onClick = {// tODO: Handle this click
                            jobScheduler.schedule(jobInfo)
                         }) {
                            Text("Schedule Job")
                        }

                    }
                }
            }
        }
    }
}

