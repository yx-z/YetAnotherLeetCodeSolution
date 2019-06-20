package easy

class Sol283 {
    fun moveZeroes(nums: IntArray) {
        var slow = 0
        while (slow < nums.size && nums[slow] != 0) {
            slow++
        }
        var fast = slow + 1
        while (fast < nums.size) {
            if (nums[fast] == 0) {
                fast++
            } else {
                nums[slow] = nums[fast]
                slow++
                fast++
            }
        }
        for (i in slow until nums.size) {
            nums[i] = 0
        }
    }
}