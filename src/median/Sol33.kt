package median

class Sol33 {

    fun search(nums: IntArray, target: Int): Int {
        var l = 0
        var h = nums.size
        while (l < h) {
            val m = l + (h - l) / 2
            val curr = if (nums[m] < nums[0] == target < nums[0]) nums[m] else
                (if (target < nums[0]) Int.MIN_VALUE else Int.MAX_VALUE)
            when {
                curr == target -> return m
                curr < target -> l = m + 1
                else -> h = m
            }
        }
        return -1
    }
}