package median

class Sol50 {

    fun myPow(x: Double, n: Int): Double = when {
        n == 0 -> 1.0
        n < 0 -> 1 / x * myPow(1 / x, -(1 + n))
        n and 1 == 0 -> myPow(x * x, n / 2)
        else -> x * myPow(x * x, n - 1)
    }
}