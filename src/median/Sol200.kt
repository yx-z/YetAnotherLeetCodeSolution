package median

class Sol200 {

    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        val (rows, cols) = (grid.size to grid[0].size)
        val visited = Array(rows) { BooleanArray(cols) }
        fun visit(r: Int, c: Int) {
            visited[r][c] = true
            if (r - 1 >= 0 && !visited[r - 1][c] && grid[r - 1][c] == '1')
                visit(r - 1, c)
            if (r + 1 < rows && !visited[r + 1][c] && grid[r + 1][c] == '1')
                visit(r + 1, c)
            if (c - 1 >= 0 && !visited[r][c - 1] && grid[r][c - 1] == '1')
                visit(r, c - 1)
            if (c + 1 < cols && !visited[r][c + 1] && grid[r][c + 1] == '1')
                visit(r, c + 1)
        }

        var count = 0
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (!visited[r][c] && grid[r][c] == '1') {
                    visit(r, c)
                    count++
                }
            }
        }
        return count
    }
}