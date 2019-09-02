package median

class Sol15 {

    fun threeSum(A: IntArray): List<List<Int>> {
        A.sort()
        val res = HashSet<List<Int>>()
        val n = A.size
        for (i in 0..n - 3) {
            val cur = A[i]
            if (cur > 0) break
            var lo = i + 1
            var hi = n - 1
            while (lo < hi) {
                val sum = A[lo] + A[hi] + cur
                when {
                    sum == 0 -> {
                        res.add(listOf(A[lo], A[hi], cur).sorted())
                        lo++
                        hi--
                    }
                    sum > 0 -> hi--
                    sum < 0 -> lo++
                }
            }
        }
        return res.toList()
    }
}