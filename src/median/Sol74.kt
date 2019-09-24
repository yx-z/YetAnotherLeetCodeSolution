package median

class Sol74 {

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val rows = matrix.size
        if (rows == 0) return false
        val cols = matrix[0].size
        if (cols == 0) return false
        var lo = 0
        var hi = rows * cols - 1
        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            val cur = matrix[mid / cols][mid % cols]
            when {
                cur == target -> return true
                cur < target -> lo = mid + 1
                else -> hi = mid - 1
            }
        }
        return false
    }
}