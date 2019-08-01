package median

class Sol6 {

    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s
        val grid = Array(numRows) { StringBuilder() }
        var i = 0
        var d = true
        var j = 0
        while (i < s.length) {
            grid[j].append(s[i])
            if (j == numRows - 1) d = false
            if (j == 0) d = true
            if (d) j++ else j--
            i++
        }
        return grid.joinToString("")
    }
}