package com.nareshtech.scorekeeper

import kotlinx.coroutines.*

fun main() = runBlocking {
    val start = System.currentTimeMillis()
    val deferred1 = async { doWork1() }
    val deferred2 = async { doWork2() }

    println("Result: ${deferred1.await()} and ${deferred2.await()}")

    val end = System.currentTimeMillis()
    println("Time taken: ${end - start} ms")
}

suspend fun doWork2():String {
    delay(1000L)
    return "Work2"
}

suspend fun doWork1():String {
    delay(1000L)
    return "work1"
}
