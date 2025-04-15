package com.nareshtech.scorekeeper

import java.util.Scanner
fun main(){
    println("Enter the size of an Array")
    val sc = Scanner(System.`in`)
    val size = sc.nextInt()
    val arr = Array<Int>(size,{i->i*2})

    for (i in arr){
        print("$i ")
    }
}