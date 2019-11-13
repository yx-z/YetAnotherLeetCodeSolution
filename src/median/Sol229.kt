package median

class Sol229 {

    fun majorityElement(nums: IntArray): List<Int> {
        val k = 3
        val M = HashMap<Int, Int>()
        for (n in nums) {
            when {
                n in M -> M[n] = M[n]!! + 1
                M.size < k -> M[n] = 1
                else -> {
                    M.keys.forEach { M[it] = M[it]!! - 1 }
                    M.keys.filter { M[it] == 0 }.forEach { M.remove(it) }
                }
            }
        }
        return M.keys.filter { c -> nums.count { it == c } > nums.size / k }
    }
}