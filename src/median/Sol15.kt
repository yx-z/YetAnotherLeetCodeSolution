package median

class Sol15 {

    fun threeSum(nums: IntArray): List<List<Int>> {
        val ans = HashSet<List<Int>>()
        for (i in nums.indices) {
            val seen = HashMap<Int, Int>() // <num, idx>
            for (j in i + 1 until nums.size) {
                if (j != i) {
                    val key = -nums[i] - nums[j]
                    if (seen.containsKey(key)) {
                        ans.add(listOf(nums[i], key, nums[j]).sorted())
                    } else {
                        seen[nums[j]] = j
                    }
                }
            }
        }
        return ans.toList()
    }
}