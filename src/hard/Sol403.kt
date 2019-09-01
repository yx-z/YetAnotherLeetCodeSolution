package hard

class Sol403 {

    fun canCross(stones: IntArray): Boolean {
        if (stones[1] != 1) return false
        val dp = HashMap<Int, HashSet<Int>>()
        stones.forEach { dp[it] = HashSet() }
        dp[1]!!.add(1)
        for (i in 1 until stones.size) {
            val s = stones[i]
            if (s in dp) {
                dp[s]!!.forEach { step ->
                    if (step + s - 1 in dp && step - 1 > 0)
                        dp[step + s - 1]!!.add(step - 1)
                    if (step + s in dp) dp[step + s]!!.add(step)
                    if (step + s + 1 in dp) dp[step + s + 1]!!.add(step + 1)
                }
            }
        }
        return dp[stones.last()]!!.isNotEmpty()
    }
}