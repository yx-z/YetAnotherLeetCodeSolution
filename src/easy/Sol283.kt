package easy

class Sol283 {

    fun moveZeroes(nums: IntArray) {
        var i = 0
        // use asSequence to avoid creating intermediate O(n) filtered list
        nums.asSequence().filter { it != 0 }.forEach { nums[i++] = it }
        (i until nums.size).forEach { nums[it] = 0 }
    }
}