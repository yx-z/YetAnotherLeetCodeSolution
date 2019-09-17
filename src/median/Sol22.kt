package median

class Sol22 {

    fun generateParenthesis(n: Int): List<String> {
        val res = ArrayList<String>()
        fun String.dfs(l: Int = 0, r: Int = 0) {
            if (length == 2 * n) {
                res.add(this)
            } else {
                if (l < n) {
                    "$this(".dfs(l + 1, r)
                }
                if (r < l) {
                    "$this)".dfs(l, r + 1)
                }
            }
        }
        "".dfs()
        return res
    }
}