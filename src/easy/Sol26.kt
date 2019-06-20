package easy

class Sol26 {
    // [0,0,1,1,1,2,2,3,3,4]

    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty() || nums.size == 1) {
            return nums.size
        }

        var d = 0
        for (i in 1 until nums.size) {
            if (nums[d] != nums[i]) {
                d++
                nums[d] = nums[i]
            }
        }
        return d + 1
    }
}
