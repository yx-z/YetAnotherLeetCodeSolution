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
}