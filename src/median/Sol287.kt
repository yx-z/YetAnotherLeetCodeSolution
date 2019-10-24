package median

class Sol287 {

    fun findDuplicate(nums: IntArray): Int {
        val n = nums.size - 1
        if (n < 1) return -1
        // n >= 1
        var slow = nums[0]
        var fast = nums[nums[0]]
        while (slow != fast) {
            slow = nums[slow]
            fast = nums[nums[fast]]
        }
        var inCycle = slow
        var outCycle = 0
        while (inCycle != outCycle) {
            inCycle = nums[inCycle]
            outCycle = nums[outCycle]
        }
        return inCycle
    }
}