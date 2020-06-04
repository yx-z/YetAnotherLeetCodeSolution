package easy

class Sol1 {

	fun twoSum(nums: IntArray, target: Int): IntArray {
		val seen = HashMap<Int, Int>()
		nums.forEachIndexed { i, n ->
			seen[target - n]?.let { return intArrayOf(it, i) }
			seen[n] = i
		}
		TODO()
	}
}