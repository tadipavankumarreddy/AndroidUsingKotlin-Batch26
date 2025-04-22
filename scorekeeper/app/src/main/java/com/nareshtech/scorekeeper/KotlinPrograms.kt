package com.nareshtech.scorekeeper

import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun performHeavyTask():String{
    println("Thread started heavy task...")
    Thread.sleep(3000)
    println("Thread finished heavy task...")
    return "Heavy task completed"
}

fun main(){
    println("Main starting the main thread..")

    // use Completable functins for cleaner way to handle results
    val futureResult = CompletableFuture<String>()

    // Create and start a new thread
    thread {
        val result = performHeavyTask()
        futureResult.complete(result)
    }

    println("Main Thread is doing other work...")
    Thread.sleep(1000)
    println("Main Thread is waiting for result...")
    val finalResult = futureResult.get(5, TimeUnit.SECONDS)
    println("Main received the result $finalResult")
}