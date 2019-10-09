package median

class Sol698 {

    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        if (k == 0) return false
        val n = nums.size
        val sum = nums.sum()
        if (sum % k != 0) return false
        val target = sum / k
        val used = BooleanArray(n)
        fun dfs(chunks: Int = k, curSum: Int = 0, lo: Int = 0): Boolean {
            if (chunks == 1) return true
            if (curSum == target) return dfs(chunks - 1)
            if (curSum > target) return false
            for (i in lo until n) {
                if (!used[i]) {
                    used[i] = true
                    if (dfs(chunks, curSum + nums[i], lo + 1)) return true
                    used[i] = false
                }
            }
            return false
        }
        return dfs()
    }
}