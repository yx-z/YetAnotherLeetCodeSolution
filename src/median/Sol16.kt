package median

import kotlin.math.abs

class Sol16 {

	fun threeSumClosest(nums: IntArray, target: Int): Int {
		val n = nums.size
		nums.sort()
		var minDiff = Int.MAX_VALUE
		var closestSum = -1
		for (pivot in nums.indices) {
			var lo = 0
			var hi = n - 1
			while (lo < hi) {
				when {
					lo == pivot -> lo++
					hi == pivot -> hi--
					else -> {
						val curSum = nums[pivot] + nums[lo] + nums[hi]
						val curDiff = abs(curSum - target)
						if (curDiff == 0) {
							return target
						}
						if (curDiff < minDiff) {
							minDiff = curDiff
							closestSum = curSum
						}
						if (curSum < target) {
							lo++
						} else {
							hi--
						}
					}
				}
			}
		}
		return closestSum
	}
}
