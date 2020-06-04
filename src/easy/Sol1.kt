package easy

class Sol1 {

	fun twoSum(nums: IntArray, target: Int): IntArray {
		val seen = HashMap<Int, Int>()
		nums.forEachIndexed { i, n ->
			if (target - n in seen) return intArrayOf(seen[target - n]!!, i)
			seen[n] = i
		}
		TODO()
	}
}