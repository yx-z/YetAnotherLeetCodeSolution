package median

class Sol59 {

    fun generateMatrix(n: Int): Array<IntArray> {
        val res = Array(n) { IntArray(n) }
        var r = 0
        var c = 0
        var dr = 0
        var dc = 1
        (1..n * n).forEach {
            res[r][c] = it
            if (res[(r + dr + n) % n][(c + dc + n) % n] != 0) {
                val tmp = dc
                dc = -dr
                dr = tmp
            }
            r += dr
            c += dc
        }
        return res
    }
}