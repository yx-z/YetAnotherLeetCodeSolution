package median

import kotlin.math.min

fun main() {
    println(Sol209().minSubArrayLen(11, intArrayOf(1, 2, 3, 4, 5)))
}

class Sol209 {

    fun minSubArrayLen(s: Int, nums: IntArray): Int {
        if (nums.sum() < s) {
            return 0
        }

        val n = nums.size
        var start = 0
        var end = 0
        var sum = nums[0]
        var count = Int.MAX_VALUE
        while (start < n) {
            if (sum >= s) {
                count = min(count, end - start + 1)
                if (count == 1) {
                    return 1
                }
                start++
                sum -= nums[start - 1]
            } else {
                when {
                    end < n - 1 -> {
                        end++
                        sum += nums[end]
                    }
                    end == n - 1 && sum < s -> return count
                }
            }
        }
        return count
    }
}