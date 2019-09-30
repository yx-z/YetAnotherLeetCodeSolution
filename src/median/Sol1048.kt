package median

import kotlin.math.max

class Sol1048 {

    fun longestStrChain(words: Array<String>): Int {
        words.sortBy { it.length }
        val dp = HashMap<String, Int>()
        var max = 0
        words.forEach { w ->
            dp[w] = w.indices.map {
                1 + (dp[w.substring(0, it) + w.substring(it + 1)] ?: 0)
            }.max()!!
            max = max(max, dp[w]!!)
        }
        return max
    }
}