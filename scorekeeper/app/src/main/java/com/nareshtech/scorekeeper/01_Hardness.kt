package com.nareshtech.scorekeeper
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter Hardness: ")
    val hardness = scanner.nextDouble()

    print("Enter Tensile Strength: ")
    val tensileStrength = scanner.nextInt()

    print("Enter Carbon Content: ")
    val carbonContent = scanner.nextDouble()
    val cond1 = (hardness > 50)
    val cond2 = (tensileStrength < 5600)
    val cond3 = (carbonContent >= 0.7)

    // Determine the grade
    val grade = when {
        cond1 && cond2 && cond3 -> 10
        cond1 && cond2  -> 9
        cond2 && cond3  -> 8
        cond1 && cond3  -> 7
        (cond1 && !cond2 && !cond3) || (!cond1 && cond2 && !cond3) || (!cond1 && !cond2 && cond3) -> 6
        else -> "Not Fit"
    }

    println("Grade: $grade")
}
