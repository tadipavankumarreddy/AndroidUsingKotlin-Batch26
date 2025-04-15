fun main() {
    print("Enter a number to check if it is in the Fibonacci series: ")
    val input = readLine()!!
    val number = input.toInt()

    var a = 0
    var b = 1
    var isFibonacci = false

    if (number == 0) {
        isFibonacci = true
    } else {
        while (b <= number) {
            if (b == number) {
                isFibonacci = true
                break
            }
            val nextNum = a + b
            a = b
            b = nextNum
        }
    }

    if (isFibonacci)
        println("$number is a Fibonacci number")
    else
        println("$number is not a Fibonacci number")
}