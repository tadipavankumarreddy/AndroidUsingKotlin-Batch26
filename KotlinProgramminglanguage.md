#### Condition Control Structure

```kotlin
package com.nareshtech.scorekeeper

import java.util.Scanner

// Decide if a number is even or not.
fun main(){
    println("Enter a value")
    val s:Scanner = Scanner(System.`in`)
    val a:Int = s.nextInt()
    // We will have to check if the entered value is even or not.
    /*if(a%2 == 0){
        println("Even Number")
    }else{
        println("Not an Even Number")
    }*/

    /*if(a%2 == 0) println("Even Number") else println("Not an Even Number")*/
    val result = if(a%2==0) true else false

    if(result) print("Even") else print("Not Even")
}
```

#### Logical Operators in Kotlin

&& - AND
|| - OR
! - NOT

***Assignment***

You are given a certain grade of steel and you are also given three values that indicate the steel's properties - Hardness, tensile strength and carbon content. You are supposed to grade the steel based on the conditions below. 

1. Hardness must be greater than 50
2. Tensile Strength must be less than 5600
3. Carbon Content must be greater than or equal to 0.7

Take the values from the user and grade it based on the guidelines below
1. If all the conditions are met, grade is 10
2. If only 1 & 2 are true, the grade is 9
3. If only 2 & 3 are true, the grade is 8
4. If only 1 & 3 are true, the grade is 7
5. If only one condition is true among the 3, the grade is 6
6. If no condition is met, the grade is "Not Fit"

#### When Expressions

`When` is like a switch in Java & C Programming language. `when` is used when you have multiple branches and when the code looks complex with these multiple branches if used with in an If condition. 

```kotlin
package com.nareshtech.scorekeeper

import java.util.Scanner
import kotlin.math.*

fun main(){
    println("Enter your choice\n1. Double the value\n2. Square\n3. Square root")
    val s:Scanner = Scanner(System.`in`)
    val option = s.nextInt()

    println("Enter the value")
    val value = s.nextInt()

    when(option){
        1 -> println(doubleValue(value))

        2-> println(powerOfTwo(value))

        3-> println(squareRoot(value))

        else -> println("Invalid option")
    }
}

fun doubleValue(a:Int) = a*2

fun powerOfTwo(a:Int) = a*a

fun squareRoot(a:Int) = sqrt(a.toDouble())
```

#### Loop Control Structure in Kotlin

When you want tp repeat a set of statements for some number of times or till the condition fails, we can employ loops. 

`in` is a keyword in kotlin that works with the range of values or a collection. 

The `for` loop

`..` -> defines a range  
`1..10` -> It means 1 to 10

```kotlin
package com.nareshtech.scorekeeper

fun main()
{
    for (i in 1..10){
        print("$i ")
    }
}
```

```kotlin
package com.nareshtech.scorekeeper

fun main()
{
    for (i in 10 downTo 1){
        print("$i ")
    }
}
```

```kotlin
fun main()
{
    for (i in 1..10 step 2){
        print("$i ")
    }
}
```

```kotlin
package com.nareshtech.scorekeeper

fun main()
{
    for (i in 10 downTo 1 step 3){
        print("$i ")
    }
}
```

```kotlin
package com.nareshtech.scorekeeper

fun main()
{
   val strings = listOf("Pavan", "Kumar", "Reddy", "Tadi")
    for(i in strings){
        print("$i ")
    }
}
```

```kotlin
package com.nareshtech.scorekeeper

// Given a list of marks of the students obtained in an exam, calculate the average marks of the list
fun main()
{
    val marks = listOf(85, 92, 78, 95, 88, 76, 90, 83, 98, 80, 65, 72, 89, 91, 79, 87, 93, 75, 82, 96,
        70, 84, 97, 81, 86, 73, 99, 77, 94, 68, 74, 86, 92, 71, 83, 88, 90, 78, 85, 69)

    // You want to find the average marks obtained by the class
    var sum = 0
    for (i in marks){
        sum += i
    }
    println("The average marks obtained by the class is ${sum/marks.size}")
    // You can find the highest mark scored in the entire class
    var max = marks[0]
    for(i in marks){
        if(i>max){
            max = i
        }
    }
    println("The highest score is $max")
    // you find the lowest mark scored in the entire class
}
```

***`while` & `do`-`while`***
```kotlin
package com.nareshtech.scorekeeper

// Given a list of marks of the students obtained in an exam, calculate the average marks of the list
fun main()
{
    // you want to print 1 to 10 numbers
    var i = 1
    while (i<=10)
    {
        print("$i ")
        i++
    }

    println()

    do{
        print("$i ")
        i--
    }while(i>=1)
}
```

##### Assignment
- Write a program to find if a given number is prime or not
- Extend the above program to identify all the palindromic prime numbers till 10,000
- Write a program to idenfity if a given number fits in fibonacci series or not. 
  - 0,1,1,2,3,5,8,13,21,34,55,89...
- Write a program to identify the factorial of any given number

***`repeat` function in kotlin***

```kotlin
fun main()
{
   repeat(10){
       print("NIIT ")
   }
}
```

#### Arrays in Kotlin
Array is one of the fundamental data structures in practically all programming languages. The idea behind an array is to store multiple pieces of the same data type, such as integers or strings , under a single name. 

Arrays are used to organize data in programming so that related set of values can easily be stored and searched for. 

***Important points to remember***
1. They are stored in contiguous memory locations. 
2. They can be accessed programmatically through their indices. 
3. They are mutable.
4. Their size is fixed
5. The index of the array starts at 0 and ends at 1 less than the actual size of the array. 

***Create an Array using arrayOf() and arrayOf<DataType>() functions***

```kotlin
package com.nareshtech.scorekeeper

fun main(){
    val a = arrayOf(1,2,33,4,5,6,7,9,8,10)
    // Get the size of the array
    println("The size of the array is ${a.size}")

    // The index starts at 0
    /*println("The first element of the array is ${a[0]}")*/
    println("The first element of the array is ${a.get(0)}")

    // The index ends at 1 less than the actual size
    println("The last item in the array is ${a[a.size-1]}")

    // What if you want to modify the existing value ?
    a.set(5,29)

    a[8] = 789

    for (i in a){
        print("$i ")
    }

}
```

***Creating an Array with `Array` Constructor***
```kotlin
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
```

***Key Differences between `arrayOf()` and `Array()`***

Feature|arrayOf()|Array Constructor
---|---|---
Length | Based on Number of Arguments| Defined Explicitly
Initialization|values are passed directly|Lambda is used to intialize the elements
Use case| Simple, fixed data| When elements need to be generated 




