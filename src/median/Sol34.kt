package median

class Sol34 {

    fun searchRange(nums: IntArray, tar: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)
        val n = nums.size
        var start = if (nums.first() == tar) 0 else -1
        if (start == -1) {
            var lo = 1
            var hi = n - 1
            loop@ while (lo <= hi) {
                val m = lo + (hi - lo) / 2
                val cur = nums[m]
                when {
                    cur == tar && nums[m - 1] != tar -> {
                        start = m
                        break@loop
                    }
                    cur >= tar -> hi--
                    else -> lo++
                }
            }
        }
        var end = if (nums.last() == tar) n - 1 else -1
        if (end == -1) {
            var lo = 0
            var hi = n - 2
            loop@ while (lo <= hi) {
                val m = lo + (hi - lo) / 2
                val cur = nums[m]
                when {
                    cur == tar && nums[m + 1] != tar -> {
                        end = m
                        break@loop
                    }
                    cur <= tar -> lo++
                    else -> hi--
                }
            }
        }
        return intArrayOf(start, end)
    }
}