package median

import kotlin.math.max

class Sol695 {

    fun maxAreaOfIsland(G: Array<IntArray>): Int {
        val (rows, cols) = G.size to G[0].size
        val DIRS = arrayOf(
            intArrayOf(-1, 0), intArrayOf(1, 0),
            intArrayOf(0, -1), intArrayOf(0, 1)
        )
        val visited = Array(rows) { BooleanArray(cols) }
        fun valid(r: Int, c: Int) = r in 0 until rows && c in 0 until cols &&
                G[r][c] == 1 && !visited[r][c]

        fun dfs(r: Int, c: Int): Int {
            visited[r][c] = true
            var area = 1
            for ((dr, dc) in DIRS) {
                val (nr, nc) = (r + dr) to (c + dc)
                if (valid(nr, nc)) area += dfs(nr, nc)
            }
            return area
        }

        var area = 0
        for (r in 0 until rows) for (c in 0 until cols)
            if (valid(r, c)) area = max(area, dfs(r, c))
        return area
    }
}