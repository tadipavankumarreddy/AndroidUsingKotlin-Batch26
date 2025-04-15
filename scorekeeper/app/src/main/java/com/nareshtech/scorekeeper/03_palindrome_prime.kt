fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    for (i in 2..n / 2) {
        if (n % i == 0)
            return false
    }
    return true
}

fun isPalindrome(n: Int): Boolean {
    val str = n.toString()
    return str == str.reversed()
}

fun main() {
    println("Palindromic prime numbers up to 10000:")
    for (num in 2..10000) {
        if (isPrime(num) && isPalindrome(num)) {
            print("$num ")
        }
    }
}