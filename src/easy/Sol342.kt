package easy

class Sol342 {
    fun isPowerOfFour(num: Int) = num > 0 &&
            (num and (num - 1) == 0) &&
            (num - 1) % 3 == 0
}