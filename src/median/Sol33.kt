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

    fun search2(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return -1
        if (nums[0] == target) return 0
        val tarLeft = nums[0] < target
        var lo = 0
        var hi = nums.size - 1
        while (lo <= hi) {
            val m = lo + (hi - lo) / 2
            val cur = nums[m]
            if (cur == target) return m
            val curLeft = nums[0] <= cur
            when {
                (curLeft && tarLeft) || (!curLeft && !tarLeft) ->
                    if (cur < target) lo++ else hi--
                tarLeft -> hi--
                else -> lo++
            }
        }
        return -1
    }
}