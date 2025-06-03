package com.nareshtech.workmanager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

// TODO 1: After adding the dependency - define a worker to create your own task
class MyWorker(context: Context, workerParameters: WorkerParameters) : Worker(context,workerParameters){

    override fun doWork(): Result {
        Log.v("Main", "Work Running")
        return Result.success()
    }

}