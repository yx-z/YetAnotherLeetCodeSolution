package median

class Sol238 {

    fun productExceptSelf(nums: IntArray): IntArray {
        val n = nums.size
        val forward = Array(n) { 1 }
        forward[0] = nums[0]
        for (i in 1 until n) {
            forward[i] = forward[i - 1] * nums[i]
        }
        val backward = Array(n) { 1 }
        backward[n - 1] = nums[n - 1]
        for (i in n - 2 downTo 0) {
            backward[i] = backward[i + 1] * nums[i]
        }
        val res = IntArray(n)
        res[0] = backward[1]
        res[n - 1] = forward[n - 2]
        for (i in 1 until n - 1) {
            res[i] = forward[i - 1] * backward[i + 1]
        }
        return res
    }

    // only allocate result space
    fun prod(nums: IntArray): IntArray {
        val n = nums.size // n > 1 as given
        val res = IntArray(n).apply {
            this[0] = 1
            this[1] = nums[0]
        }
        for (i in 2 until n) res[i] = res[i - 1] * nums[i - 1]
        // now res[i] = prod(nums[0 until i])
        var r = 1
        for (i in n - 1 downTo 0) {
            res[i] *= r
            r *= nums[i]
        }
        return res
    }
}