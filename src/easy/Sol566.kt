package easy

class Sol566 {

    fun matrixReshape(n: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
        if (r * c != n.size * n[0].size) return n
        val M = Array(r) { IntArray(c) }
        for (i in 0 until r * c)
            M[i / c][i % c] = n[i / n[0].size][i % n[0].size]
        return M
    }
}