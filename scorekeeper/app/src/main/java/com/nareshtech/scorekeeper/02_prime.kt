fun main() {
    print("Enter a number: ")
    val input = readLine()!!
    val number = input.toInt()

    var isPrime = true
    if (number <= 1) {
        isPrime = false
    } else {
        for (i in 2..number / 2) {
            if (number % i == 0) {
                isPrime = false
                break
            }
        }
    }
    if (isPrime)
        println("$number is a prime number")
    else
        println("$number is not a prime number")
}