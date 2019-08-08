package easy

class Sol977 {

    fun sortedSquares(A: IntArray): IntArray {
        val n = A.size
        if (n == 0) {
            return A
        }
        val res = IntArray(n)
        var nonneg = A.indexOfFirst { it >= 0 }
        if (nonneg == -1) {
            for (i in A.indices) {
                res[n - i - 1] = A[i] * A[i]
            }
            return res
        }
        var neg = nonneg - 1
        var i = 0
        while (nonneg < n && neg >= 0) {
            val negSquared = A[neg] * A[neg]
            val nonnegSquared = A[nonneg] * A[nonneg]
            if (negSquared < nonnegSquared) {
                res[i] = negSquared
                neg--
            } else {
                res[i] = nonnegSquared
                nonneg++
            }
            i++
        }
        if (neg < 0) {
            // remains nonneg
            while (nonneg < n) {
                res[i] = A[nonneg] * A[nonneg]
                nonneg++
                i++
            }
        } else {
            // remains neg
            while (neg >= 0) {
                res[i] = A[neg] * A[neg]
                neg--
                i++
            }
        }
        return res
    }
}