package median

class Sol78 {

    fun subsets(nums: IntArray, start: Int = 0): List<List<Int>> {
        if (start == nums.size - 1) {
            return listOf(listOf(nums[start]), emptyList())
        }

        val first = nums[start]
        val rest = subsets(nums, start + 1)
        val extended = rest.map { it.toMutableList() }
        extended.forEach { it.add(first) }
        return rest + extended
    }
}