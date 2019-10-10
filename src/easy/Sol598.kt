package easy

class Sol598 {
    fun maxCount(m: Int, n: Int, ops: Array<IntArray>) =
        (ops.map { it[0] }.min() ?: m) * (ops.map { it[1] }.min() ?: n)
}