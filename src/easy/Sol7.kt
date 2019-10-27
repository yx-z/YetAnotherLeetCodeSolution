package easy

class Sol7 {

    fun reverse(x: Int): Int {
        var res = 0L
        var cur = x
        while (cur != 0) {
            res *= 10
            res += cur % 10
            cur /= 10
        }
        return if (res in Int.MIN_VALUE..Int.MAX_VALUE) res.toInt() else 0
    }
}
