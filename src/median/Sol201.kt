package median

class Sol201 {

    fun rangeBitwiseAnd(m: Int, n: Int): Int =
        if (m == n) {
            m
        } else {
            rangeBitwiseAnd(m / 2, n / 2) shl 1
        }
}