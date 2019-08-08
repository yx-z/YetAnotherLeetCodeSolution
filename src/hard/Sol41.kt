package hard

class Sol41 {

    // for n numbers, the smallest missing positive integer is at most n + 1
    fun firstMissingPositive(nums: IntArray): Int {
        val n = nums.size
        val dict = BooleanArray(n + 2).apply { this[0] = true }
        nums.filter { it in 1..n }.forEach { dict[it] = true }
        return dict.indexOfFirst { !it }
    }

    fun firstMissingPositiveInPlace(nums: IntArray): Int {
        val n = nums.size
        // for each number i, we wish to place it at nums[i - 1]
        for (i in nums.indices) {
            while (nums[i] in 1..n && nums[nums[i] - 1] != nums[i]) {
                nums.swap(i, nums[i] - 1)
            }
        }
        return (1..n).firstOrNull { nums[it - 1] != it } ?: n + 1
    }

    fun IntArray.swap(i: Int, j: Int) {
        val tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }
}