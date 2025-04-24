package com.nareshtech.scorekeeper

import kotlinx.coroutines.*

/***
 * Task A: Fetch user from Database
 * Task B: Send welcome email to that user.
 */

suspend fun fetchUser():User {
    delay(5000)
    return User("John", "Doe")

}
suspend fun sendWelcomeEmail(user:User){
    delay(500)
    println("Welcome ${user.s} ${user.s1}")
}
data class User(val s: String, val s1: String)

fun main() = runBlocking {
    val userDeferred = async { fetchUser() }
    val user = userDeferred.await()
    sendWelcomeEmail(user)
}
