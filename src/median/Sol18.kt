package median

class Sol18 {

	fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
		nums.sort()
		val res = HashSet<List<Int>>()
		val n = nums.size
		for (i in nums.indices) {
			for (j in i + 1 until n) {
				val t = target - nums[i] - nums[j]
				var lo = 0
				var hi = n - 1
				while (lo < hi) {
					when {
						lo == i || lo == j -> lo++
						hi == i || hi == j -> hi--
						else -> {
							val curSum = nums[lo] + nums[hi]
							when {
								curSum == t -> {
									res.add(listOf(nums[lo], nums[hi], nums[i], nums[j]).sorted())
									lo++
									hi--
								}
								curSum < t -> lo++
								curSum > t -> hi--
							}
						}
					}
				}
			}
		}
		return res.toList()
	}
}