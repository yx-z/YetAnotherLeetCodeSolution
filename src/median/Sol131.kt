package median

class Sol131 {

    fun partition(s: String): List<List<String>> {
        val l = s.length
        if (l == 0) {
            return emptyList()
        }

        val pal = Array(l) { Array(l) { false } }
        for (i in 0 until l) {
            pal[i][i] = true
            if (i >= 1 && s[i] == s[i - 1]) {
                pal[i - 1][i] = true
            }
        }
        for (i in l - 3 downTo 0) {
            for (j in i + 2 until l) {
                pal[i][j] = (s[i] == s[j]) && pal[i + 1][j - 1]
            }
        }

        val dp = Array<ArrayList<List<String>>>(l) { ArrayList() }
        dp[l - 1] = arrayListOf(listOf(s[l - 1].toString()))
        for (i in l - 2 downTo 0) {
            for (j in i until l) {
                if (pal[i][j]) {
                    if (j == l - 1) {
                        dp[i].add(listOf(s.substring(i until l)))
                    } else {
                        dp[i].addAll(dp[j + 1].map {
                            ArrayList(it).apply { add(0, s.substring(i..j)) }
                        })
                    }
                }
            }
        }
        return dp[0]
    }
}