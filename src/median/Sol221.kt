package median

import kotlin.math.max
import kotlin.math.min

class Sol221 {

    fun maximalSquare(matrix: Array<CharArray>): Int {
        val rows = matrix.size
        if (rows == 0) {
            return 0
        }
        val cols = matrix[0].size
        var maxSide = 0
        val dp = Array(rows) { IntArray(cols) }
        for (r in 0 until rows) {
            if (matrix[r][cols - 1] == '1') {
                dp[r][cols - 1] = 1
                maxSide = 1
            }
        }
        for (c in 0 until cols) {
            if (matrix[rows - 1][c] == '1') {
                dp[rows - 1][c] = 1
                maxSide = 1
            }
        }
        for (r in rows - 2 downTo 0) {
            for (c in cols - 2 downTo 0) {
                if (matrix[r][c] == '1') {
                    dp[r][c] = 1 + min(
                        dp[r + 1][c + 1],
                        min(dp[r + 1][c], dp[r][c + 1])
                    )
                    maxSide = max(maxSide, dp[r][c])
                }
            }
        }
        return maxSide * maxSide
    }
}
