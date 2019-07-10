package median

class Sol454 {

    fun fourSumCount(A: IntArray, B: IntArray, C: IntArray, D: IntArray): Int {
        val n = A.size
        // <sum, count>
        val mapAB = HashMap<Int, Int>()
        val mapCD = HashMap<Int, Int>()
        for (i in 0 until n) {
            for (j in 0 until n) {
                mapAB[A[i] + B[j]] = (mapAB[A[i] + B[j]] ?: 0) + 1
                mapCD[C[i] + D[j]] = (mapCD[C[i] + D[j]] ?: 0) + 1
            }
        }

        var count = 0
        for (k in mapAB.keys) {
            if (mapCD.containsKey(-k)) {
                count += mapAB[k]!! * mapCD[-k]!!
            }
        }
        return count
    }
}