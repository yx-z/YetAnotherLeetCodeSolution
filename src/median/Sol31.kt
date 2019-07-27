package median

class Sol31 {

    fun nextPermutation(nums: IntArray) {
        val n = nums.size
        val swap = { i: Int, j: Int ->
            val tmp = nums[i]
            nums[i] = nums[j]
            nums[j] = tmp
        }
        var p = -1
        for (i in n - 1 downTo 1) {
            if (nums[i - 1] < nums[i]) {
                p = i - 1
                break
            }
        }
        if (p != -1) {
            var x = 0
            for (i in n - 1 downTo 0) {
                if (nums[i] > nums[p]) {
                    x = i
                    break
                }
            }
            swap(p, x)
        }
        var s = p + 1
        var e = n - 1
        while (s < e) {
            swap(s, e)
            s++
            e--
        }
    }
}