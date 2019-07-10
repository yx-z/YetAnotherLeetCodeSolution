package median

import java.util.*


class Sol378 {

    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val n = matrix.size
        val heap = PriorityQueue<Int>()
        for (i in 0 until n) {
            for (j in 0 until n) {
                heap.add(matrix[i][j])
            }
        }
        for (i in 0 until k - 1) {
            heap.poll()
        }
        return heap.poll()
    }
}