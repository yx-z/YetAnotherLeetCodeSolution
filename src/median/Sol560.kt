package median

import java.util.*


class Sol560 {

    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0
        var sum = 0
        val map = HashMap<Int, Int>()
        map[0] = 1
        for (i in nums.indices) {
            sum += nums[i]
            count += map[sum - k] ?: 0
            map[sum] = (map[sum] ?: 0) + 1
        }
        return count
    }
}