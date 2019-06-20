package median

class Sol46 {

    fun permute(nums: IntArray, start: Int = 0): List<List<Int>> {
        if (start == nums.size - 1) return listOf(listOf(nums[nums.size - 1]))
        return permute(nums, start + 1).flatMap { l ->
            (0..l.size).map { ArrayList(l).apply { add(it, nums[start]) } }
        }
    }
}
