package com.nareshtech.scorekeeper

fun main() {
    val words = listOf("apple", "banana", "apricot", "blueberry")
    val grouped = words.groupBy { it.first() }
    print(grouped)
}