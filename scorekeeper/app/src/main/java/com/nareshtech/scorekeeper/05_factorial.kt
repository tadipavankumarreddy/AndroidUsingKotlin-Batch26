fun main() {
    print("Enter a number to compute its factorial: ")
    val input = readLine()!!
    val number = input.toInt()

    var factorial: Long = 1
    for (i in 1..number) {
        factorial *= i
    }

    println("Factorial of $number is $factorial")
}
