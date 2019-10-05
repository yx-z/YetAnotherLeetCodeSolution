package median

class Sol240 {

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix.any { it.isEmpty() }) {
            return false
        }

        val rows = matrix.size
        val cols = matrix[0].size

        var colIdx = 0
        var rowIdx = rows - 1
        while (colIdx < cols && rowIdx >= 0) {
            val curr = matrix[rowIdx][colIdx]
            when {
                curr == target -> return true
                curr > target -> rowIdx--
                else -> colIdx++
            }
        }

        return false
    }
}