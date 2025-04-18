package com.nareshtech.scorekeeper

class A{
    init {
        increment()
    }
    companion object{
        var count = 0
        fun increment(){
            count++
        }
    }
}


fun main(){
    val a1 = A()
    val a2 = A()
    val a3 = A()
    println(A.count)
}
