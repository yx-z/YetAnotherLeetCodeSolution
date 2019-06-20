package median

class Sol39 {

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val dp = Array(target + 1) { ArrayList<List<Int>>() }
        candidates.filter { it <= target }.forEach { dp[it] + listOf(it) }
        (1..target).forEach { t ->
            candidates.filter { it <= t }.forEach { c ->
                dp[t].addAll(dp[t - c].map { it + c })
            }
        }
        return dp[target].map { it.sorted() }.distinct()
    }
}
