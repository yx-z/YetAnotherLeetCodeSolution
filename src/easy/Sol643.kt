package easy

import kotlin.math.max

class Sol643 {

    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var maxSum = nums.take(k).sum()
        var curSum = maxSum
        for (i in k until nums.size) {
            curSum = curSum + nums[i] - nums[i - k]
            maxSum = max(maxSum, curSum)
        }
        return maxSum / k.toDouble()
    }
}