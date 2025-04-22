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

#### Encapsulation
Encapsulation is a fundamental concept in OOP. It is used to hide the internal implementation details of a class and only expose what is necessary to the outside world. This leads to better data security, code maintaenance and modularity. 

***Why encapsulation is needed in Kotlin ?***
1. Protect data: Prevents direct access to class fields, avoiding accidental or unauthorized changes.
2. Improves Maintainability: Internal logic can be changed without affecting external code. 
3. Reduces complexity: only relevant data details are exposed, making the code easier to understand. 
4. Control Over Data
5. Promtes reusability & Testability

***Modifiers in Kotlin***
- private - The element that is declared private cannot be accessed outside the kotlin file or class that is defined in. These elements can only be accessed in the same place where they are defined. 
  - The private modifier restricts visibility to 
      - Within the same class or Object (for class members)
      - within the same file (for top level declarations like functions or classes)
- public - Elements can be accessed from anywhere
- protected - Same as private except that subclasses can access the classs/interface elements.
- internal - Anything in the module (folder) can be accessed. 

***Reference Code:***
```kotlin
class Internals{
    internal var a = 10
}

fun main(){
    val i = Internals()
    println(i.a)
}
```

***Example***
```kotlin
package com.nareshtech.scorekeeper

class BankAccount(private var balance:Double){
    fun deposit(amount:Double){
        if(amount>0){
            balance+=amount
            println("Deposited $amount. New balance is $balance")
        }else{
            println("Invalid deposit amount")
        }
    }
    
    fun withdraw(amount:Double){
        if(amount>0 && amount<=balance){
            balance-=amount
            println("Withdrew $amount. New balance is $balance")
        }else{
            println("Invalid withdrawal amount or insufficient balance")
        }
    }
    
    fun getBalance():Double{
        return balance
    }
}
```

By using encapsulation, the internal state of the `BankAccount` class is protected from direct access and modifications from outside the class. Only the methods provided by the class can modify the balance, ensuring that the class maintains control over how the balance is accessed and changed. This helps maintain integrity of data and prevents unintended side effects. 

If a class with a certain functionality is defined in a different package and the elements in the class are accessible either directly (if they are declared public) or with the help of their helper methods (incase private or protected modifiers), you can write the import statements to import that module or a specific class. 

If the class in the same module (package) needs to be accessed, you can directly access it without needing an import statement. 

#### Polymorphism

Existence in multiple forms
- overloading (compile-time polymorphism)
- overriding (runtime polymorphism)

***Overloading***
```kotlin
package com.nareshtech.scorekeeper

fun sum(a:Int, b:Int) = a+b

fun sum(a:Int, b:Int, c:Int) = sum(a,b)+c

fun sum(a:Int, b:Double):Double = a+b

fun main(){
    println(sum(10,20))
    println(sum(10,20,30))
    println(sum(10,20.89))
}
```

***Overriding***

The function that has `open` keyword can only be overridden. 

```kotlin
open class First{
    open fun sum(a:Int, b:Int) = a+b

    fun sum(a:Int, b:Int, c:Int) = sum(a,b)+c
}

class Second:First(){
    override fun sum(a:Int, b:Int) = a*b
}

fun main(){
    var s = Second()
    println(s.sum(10,20))
    println(s.sum(10,20,30))
}
```

#### Abstract classes in Kotlin

Abstract classes are those classes defined with abstract keyword. In an abstract class you can have methods with body and methods without a body. 

Abstract classes cannot be instantiated on its own and must be subclassed. 

```kotlin
package com.nareshtech.scorekeeper

abstract class RBI{
    fun homeloanInterestrate():Double{
        return 8.5
    }

    abstract fun personalloanInterestrate():Double
}

class SBI : RBI(){
    override fun personalloanInterestrate(): Double {
        return 11.0
    }
}

class ICICI : RBI(){
    override fun personalloanInterestrate(): Double {
        return 12.0
    }
}

fun main(){
    val s:SBI = SBI()
    println(s.homeloanInterestrate())
    println(s.personalloanInterestrate())
}
```

#### Interfaces in Kotlin
Interfaces are similar to interfaces in Java, but with some additonal features and more concise syntax. 

***Declaring an Interface***

```kotlin
interface MyInterface{
    fun myMethod()
    val myProperty:String
}
```

***Implementing an Interface***
```kotlin
interface MyInterface{
    fun myMethod()
    val myProperty:String
}

class MyClass:MyInterface {
    override val myProperty:String = "Hello"
    override fun myMethod() {
        println("This is my method")
    }
}
```

***Default Implementation***
Kotlin Interfaces can provide default implementation for methods. 

```kotlin
interface MyInterface{
    fun myMethod(){
        println("This is my method1")
    }
    val myProperty:String
}

class MyClass:MyInterface {
    override val myProperty:String = "Hello"
    override fun myMethod() {
        println("This is my method2")
    }
}
```

***With interfaces, we can implement multiple inheritance***

```kotlin
interface A{
    fun greet(){
        println("Greet from A")
    }
}

interface B{
    fun greet(){
        println("Greet from B")
    }
}

class C:A, B{
    override fun greet() {
        super<A>.greet()
        super<B>.greet()
        println("Greet From Class C")
    }
}

fun main(){
    val obj = C()
    obj.greet()
}
```

#### Companion Object in Kotlin
In Kotlin, a companion object is an **object that is declared inside a class** using the companion keyword.
It allows you to **define members (like functions and variables) that belong to the class itself, not to an instance (object) of the class**- similar to static members in java. 

***Why we use a companion Object ?***

- To define constants or utility functions at the class level. 
- To create factory methods or singleton patterns
- To access members without creating an object of the class. 


***Syntax***

```kotlin
class MyClass{
    companion object{
        const val CONST_VAL = 10
        fun showMessage(){
            println("Hello from companion object)
        }
    }
}
```

```kotlin
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
```

#### Lambdas and Higher Order Functions
***What are Lambdas ?***
A Lambda expression is a function that can be passed as a parameter and does not need to be declared explicitly. 

They are anonymous functions. 

**Syntax of a Lambda**

```kotlin
val lambdaName : (InputValue) - > ReturnType = {arguments -> body}
```

**Example**
```kotlin
fun main(){

    val greet:(String) -> Unit = {name -> println("Helllo, $name")}

    greet("Kotlin")
}
```

***Simplified Lambda Syntax***

Kotlin allows us to simplify Lambdas further

**Remove Explicit Type**

```kotlin
val greet = {name:String -> println("Helllo, $name")}
    greet("Kotlin")
```

**Single Parameter shortcut (it)**
When there's only one parameter, you can use `it`:

```kotlin
fun main(){
    val square = {it:Int -> it*it}
    println(square(20))
}
```
---

#### Higher-Order function
A Higher order function is a function that takes another function as a parameter or return a function. 

Example 1: Passing a lambda as a parameter

```kotlin
fun main(){
    val sum = calculate(4,5){x,y -> x+y}
    println("Sum: $sum")

    val product = calculate(4,5){x,y -> x*y}
    println("Product: $product")
}

fun calculate(a:Int, b:Int, operation:(Int, Int) -> Int):Int{
    return operation(a,b)
}
```

***Explaination***
1. `calculate` takes two integers (a , b) and a lambda funciton ``operation`.
2. `operation` performs an action on a and b.

---

**Returning a Function**

 A function can return another function

 **Example**

```kotlin
fun main() {
    val timesThree = createMultiplier(3)
    val timesFive = createMultiplier(5)

    println(timesThree(10))
    println(timesFive(20))
}

fun createMultiplier(multiplier:Int):(Int) -> Int{
    return {number -> number * multiplier}
}
```
**Example**  
***String Formatting using a higher order function***
```kotlin
fun main() {
    val upper = formatString("naresh"){it -> it.uppercase()}
    val lower = formatString("Naresh"){it -> it.lowercase()}
    val reverse = formatString("Naresh"){it -> it.reversed()}
    println(" $upper \n $lower \n $reverse")
}

fun formatString(value:String, formatter:(String) -> String):String{
    return formatter(value)
}
```
**Example**  
***List Processing***
```kotlin
fun main() {
    val originalList = listOf(1,2,3,4,5,6,7,8,9,10)
    val squaredList = processList(originalList){it*it}
    val doubleEach = processList(originalList){it*2}
    println("original List $originalList")
    println("Squared List $squaredList")
    println("Doubled List $doubleEach")
}

fun processList(list: List<Int>, process:(Int)->Int):List<Int>{
    return list.map(process)
}
```

**Example**  
```kotlin
fun main() {
   repeatAction(10){println("Naresh")}
}

fun repeatAction(times:Int, action:()-> Unit){
    repeat(times){action()}
}
```

**Example**   
```kotlin
fun main() {
   val isEmail = validate("test@example.com"){it.contains("@")}
    println(isEmail)

    val isEmailHavingDot = validate("test@example.com"){it.contains(".")}
    println(isEmailHavingDot)
}

fun validate(input:String, rule:(String) -> Boolean):Boolean{
    return rule(input)
}
```

---

***Common Use Cases***

**Example 1: Filtering a List**
```kotlin
fun main() {
    val numbers = listOf(1,2,3,4,5,6,7,8,9,10)
    val evenNumbers = numbers.filter { it%2 == 0 }
    val notEvenNumbers = numbers.filter { it%2!==0 }

    println("Original list: $numbers")
    println("Even numbers: $evenNumbers")
    println("Not Even Numbers: $notEvenNumbers")
}
```

**Example 2: Mapping a list**
```kotlin
val doubled = numbers.map { it*2 }
println(doubled)
```

**Example 3: Sorting a list**

```kotlin
fun main() {
    val names = listOf("Alice", "Charlie", "Bob")
    val sortedNames = names.sorted()
    println(sortedNames)

    // Sort the data based on length
    val sortedNamesByLength = names.sortedBy { it.length }
    println(sortedNamesByLength)
}
```

**Example 4: for each**  

```kotlin
fun main() {
    val fruits = listOf(1,2,3,4,5,6,7,8,9,10)
    fruits.forEach { print("${it%2} ") }
}
```

**Example 5: groupBy**
```kotlin
fun main() {
    val words = listOf("apple", "banana", "apricot", "blueberry")
    val grouped = words.groupBy { it.first() }
    print(grouped)
}
```

#### Assignments 
1. Create a calculator using Higher Order functions
   - **Task**: Write a function calculate that takes two Integers and a lambda that defines the operations (+,-,*,/).
2. Custom map() function
   - **Task**:Write a function that mimics the behavior of kotlin's built in map() function
3. List operation with conditions
   - **Task**:Given a list of strings, use filter, map and sortedBy to
     - Filter names longer than 3 letters
     - Convert them to uppercase
     - sort by length
    - Example Input:  ["Tom", "Jerry","Ana","Kotlin","Bob"]
    - Ouput: ["JERRY","KOTLIN"]

Please explore other in built higher order functions such as `let`, `apply` and `run`.

#### Data classes in KOtlin
In Many programming scenarios, you create classes whose main purpose is to hold data. These classes often require you to write repetitive code for common functionalities like:
- `euquals()` and `hashcode()` to compare instances(Objects) for equality
- `toString()` to get human-readable representation of the object. 
- `componentN()` functions for destructing declarations.
- `copy()` to create a new instance with some properties changed. 

Kotlin's data classes automatically generate these functions for you based on the properties declared in the primary constructor. 

This makes code much clearer, more concise, and less error-prone. 

***Syntax:***
```kotlin
data class User(val name:String, val age:Int)
```

***ComponetN() functions***
```kotlin
data class User(val name:String, val age:Int)

fun main(){
    val user = User("Diana", 28)
    val userName = user.component1()
    val age = user.component2()
    println("$userName $age")
}
```

***`copy()` function***
- This function allows you to create a new instance of the data class with some or all of its properties changed, while keeping the others the same. 
- It provides default values for all the primary constructor parameters, allowing you to selectively override them.

```kotlin
data class User(val name:String, val age:Int)

fun main(){
    val user1 = User("Diana", 28)
    val user2 = user1.copy(age = 52)
    val user3 = user1.copy(name = "John")
    val user4 = user1.copy() // this creates the exact copy

    println(user1)
    println(user2)
    println(user3)
    println(user4)
}
```

**Requirements for the Dataclass**  
To be eligible to be a dataclass, a class must satisfy the following requirements:
- The primary constructor needs to have at least one parameter
- All primary constructor parameters need to be marked as `val` or `var`
- Data classes cannot be `abstract`, `open`, `sealed` or `inner`.

**Points to remember**
Only the properties declared in the primary constructor are used by the compiler to automatically generate `equals()`, `hashcode()`, `toString()`, `componentN()` and `copy()` functions. Properties defined inside the class body (not in the primary constructor) are not considered for these generated functions. 

```kotlin
data class Person(val firstName:String, val lastName:String){
    var fullName = "$firstName $lastName"
}

fun main()
{
    val person1 = Person("Naresh", "Tech")
    val person2 = person1.copy(lastName = "IT")
    println(person1.fullName)
    println(person2.fullName)

}
```

***use cases for data class***
- `Data Transfer Objects (DTOs)` Representing data being transferred between different parts of the application or across network boundaries. 
- `Entities:` Holding data representing objects in your domain model. 
- `Configuration Objects:` storing application settings. 
- `Records:` simple structures to hold related pieces of information.
- `Working with COllections:` The automatically generated `equals()` and `hashcode()` make it easy to compare and manage collections of data class instances. 


#### Thread
- A thread represents an independent path of execution within a program. 
- This is in place to achieve concurrency. 

```kotlin
package com.nareshtech.scorekeeper

fun main(){
    println("Main Thread started")

    // I want to create a worker thread for this main program to carryout a simple task.
    val myThread = Thread{
        // define what has to happen inside this block.
        println("Worker Thread started")
        Thread.sleep(2000)
        println("Worker Thread finished")
    }

    myThread.start()

    println("Main Thread continues.")
    Thread.sleep(1000)
    println("Main Thread finished")
}
```

```kotlin
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
```



