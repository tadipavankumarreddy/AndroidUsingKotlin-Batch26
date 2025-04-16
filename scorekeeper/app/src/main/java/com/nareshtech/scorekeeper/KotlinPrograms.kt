package com.nareshtech.scorekeeper

open class A(var name:String?, var age:Int?){
    fun display(){
        println("Name: $name Age: $age")
    }
}

class B(var n:String?, var a:Int?, var salary:Int?):A(n,a){
    fun display2(){
        display()
        println("$salary")
    }
}

fun main(){
    val b = B("Pavan",33,10000)
    b.display2()
}