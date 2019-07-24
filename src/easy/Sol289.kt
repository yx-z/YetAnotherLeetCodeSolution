package easy

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Sol289 {
    private val liveDead = -1
    private val deadDead = 0
    private val liveLive = 1
    private val deadLive = 2

    fun gameOfLife(grid: Array<IntArray>) {
        for (i in grid.indices) for (j in grid[0].indices)
            if (abs(grid[i][j]) == liveLive) {
                when (grid.count(i, j)) {
                    0, 1 -> grid[i][j] = liveDead
                    2, 3 -> {
                    }
                    else -> grid[i][j] = liveDead
                }
            } else {
                if (grid.count(i, j) == 3) grid[i][j] = deadLive
            }
        for (i in grid.indices) for (j in grid[0].indices)
            when (grid[i][j]) {
                liveDead -> grid[i][j] = deadDead
                deadLive -> grid[i][j] = liveLive
            }
    }

    fun Array<IntArray>.count(i: Int, j: Int) =
        (max(0, i - 1)..min(size - 1, i + 1)).sumBy { x ->
            (max(0, j - 1)..min(this[0].size - 1, j + 1)).count { y ->
                x to y != i to j && abs(this[x][y]) == 1
            }
        }
}