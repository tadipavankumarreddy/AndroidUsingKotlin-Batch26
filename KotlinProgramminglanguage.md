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

#### OOPs in Kotlin

***OOP - Object Oriented Programming***

Procedural programming is about writing the procedures or methods that operate on data while, OOP is about creating objects that contain both method and data.

***Advantages***
- Faster and easier to execute
- Provides clear structure of the program
- OOP helps to keep the kotlin code DRY (Dont repeat yourself)
- Reusabilty

***Kotlin Objects and Classes***

- Any real world entity that shows attributes and behaviors can considered as an Object.
- A class is a blueprint of an Object. 

Unlike Java, Kotlin is null safe. That is the reason, the variables that you declare must be intialized or they can also accept null values if your specify explicitly with `?` combination.

```kotlin
package com.nareshtech.scorekeeper

import java.util.Scanner

class Dog{
    // Dog Attributes -> name, age & Weight
    var name:String? = null
    var age:Int? = null
    var weight:Double? = null

    fun display(){
        println("$name of age $age is of weight $weight")
    }
}

fun main(){
    val d = Dog() // This is the place where you created a copy of the class and assigned with some memory
    d.name = "Tommy"
    d.age = 2
    d.weight = 20.5
    d.display()

    val d2 = Dog()
    d2.name = "Max"
    d2.age = 3
    d2.weight = 25.5
    d2.display()
}
```


#### Constructors in Kotlin
In Kotlin, A Constructor is a special block of code that initializes a new object of the class. There are two types of constructors in Kotlin. 

1. Primary constructor
- The primary constructor is a part of the class header and is declared after the class name. 
- It is used to initialize the class with basic parameters
2. Secondary Constructor
- A class can have as many constructors as it needs, which are defined inside the class body using `constructor` keyword. 
- They can provide additional initialization logic or different ways to initialize an Object. 

```kotlin
package com.nareshtech.scorekeeper

import java.util.Scanner

class Dog(var name:String?, var age:Int?, var weight:Double?){

    constructor(name:String):this(name,0,0.0){
        println("Remember that you only entered the name of the dog")
    }

    constructor(name:String,age:Int):this(name,age,0.0){
        println("Remember that you only entered the name and age of the dog")
    }

    fun display(){
        println("$name of age $age is of weight $weight")
    }
}

fun main(){
    val d = Dog("Tommy",2,20.5) // This is the place where you created a copy of the class and assigned with some memory
    d.display()

    val d2 = Dog("Max",3)
    d2.display()
}
```

#### Init block in kotlin
In Kotlin, the `init` block is used to initialize class properties or execute logic during object creation. It runs immediately after the primary constructor is called before any secondary consturctors. 

Why do we need to use `init` block ?
- To validate the inputs
- To perform computations during the object creation. 
- To log/debug creation flow. 

```kotlin
package com.nareshtech.scorekeeper

import java.util.Scanner

class Person(val name: String, val age: Int){
    init {
        println("Person is created: $name is $age years old")
        // Want to validate age -> because age must be greater than 0
        require(age>=0){"Age must be positive"} 
        // This is not exclusive to init block, you can literally use require(...) anywhere to validate a certain input and throw an illegalArgumentException if it fails.
    }
}

fun main(){
    val pavan = Person("Pavan", -1)
}
```

***What happens ?***
- Primary constructor parameters(name,age) are assigned. 
- Init block executes
  - printing the message
  - validating the age


***Multiple Init blocks***
You can have more than one, and they execute in the oder they appear

```kotlin
package com.nareshtech.scorekeeper

import java.util.Scanner

class Sample{
    init {
        println("First Init")
    }
    init {
        println("Second Init")
    }
}

fun main(){
    val sample = Sample()
}
```
#### Inheritance in Kotlin
- Inheritance is the process of acquring the properties and behaviors of one class into another class. 
- Inheritance is primarily used for Re-usability of the code. 

***Important Note:***
- All classes in kotlin have a common super class `Any`, which is the default super class for a class with no super class defined. 

```kotlin
class ABCD{
    // The default super class will be Any
}
```

`Any` class has got three methods
- equals()
- hashCode()
- toString()

```kotlin
package com.nareshtech.scorekeeper

class Dog(var name:String?, var age:Int?, var weight:Double?){

    constructor(name:String):this(name,0,0.0){
        println("Remember that you only entered the name of the dog")
    }

    constructor(name:String,age:Int):this(name,age,0.0){
        println("Remember that you only entered the name and age of the dog")
    }

    fun display(){
        println("$name of age $age is of weight $weight")
    }
}

fun main(){
    val d = Dog("Tommy",2,20.5) // This is the place where you created a copy of the class and assigned with some memory
    val d2 = Dog("Tommy",2,20.5)
    val d3 = d
    println(d.equals(d2))
    println(d.equals(d3))
    println(d.toString())
    println(d.hashCode())
    println(d2.hashCode())
}
```

TO get a meaningful output with the toString() method of `Any` class, use it with data classes

```kotlin
data class User(val name: String, val age: Int)

fun main(){
    val user = User("John",22)
    println(user.toString())
}
```

```kotlin
data class User(val name: String, val age: Int)

fun main(){
    val user = User("John",22)
    val user2 = User("John",22)
    println(user.hashCode())
    println(user2.hashCode())
}
```

***Summary Table***

Feature|Regular Class|Data class
---|---|---
toString()|Memory address (e.g., User@1a2g234)|Meaningful string with property values
hashCode()|Based on the memory reference|Based on the property values in the constructor

***Important Point:*** In kotlin all classes are `final` by default. If you want to subclass a class you have to use `open` keyword to make the class subclassable. 

```kotlin
class Pavan{
    // This class is final and cannot be subclassed
}
```

```kotlin
open class Pavan{
    // this class can be extended by a subclass.
}
```

In `Java` we use `extends` keyword to create a subclass. In Kotlin we use `:` instead of `extends`.

```kotlin
open class Pavan{
    // define something here
}

class kumar:Pavan(){
    // All the features of Pavan comes here
}
```

Example
```kotlin
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
```

