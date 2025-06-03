package com.nareshtech.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast

// TODO 2
class MyJobService: JobService() {
    // These methods run on UI Thread (Main Thread) - Returns boolean value
    // onStartJob -> True -> When the job offloaded to a worker thread
    // -> false - when the job is finished.
    override fun onStartJob(p0: JobParameters?): Boolean {
        Toast.makeText(applicationContext,"Job is Finished", Toast.LENGTH_LONG).show()
        return false
    }


    // True -> when the job needs to be rescheduled
    // Flase -> When there is no need of rescheduling.
    override fun onStopJob(p0: JobParameters?): Boolean {
        Toast.makeText(applicationContext,"Job is cancelled", Toast.LENGTH_LONG).show()
        return false
    }


}