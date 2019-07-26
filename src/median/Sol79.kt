package median

class Sol79 {

    fun exist(b: Array<CharArray>, s: String): Boolean {
        val (m, n) = b.size to b[0].size
        val v = Array(m) { BooleanArray(n) }
        fun dfs(r: Int, c: Int, i: Int = 0): Boolean {
            if (v[r][c] || i == s.length || b[r][c] != s[i]) return false
            if (i == s.length - 1) return true
            v[r][c] = true
            return (r > 0 && dfs(r - 1, c, i + 1)) ||
                    (r + 1 < m && dfs(r + 1, c, i + 1)) ||
                    (c > 0 && dfs(r, c - 1, i + 1)) ||
                    (c + 1 < n && dfs(r, c + 1, i + 1)).also { v[r][c] = false }
        }
        return (0 until m).any { i -> (0 until n).any { dfs(i, it) } }
    }
}