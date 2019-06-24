package median

class Sol75 {

    // to rephrase: sort `nums` in-place with one pass
    // where `nums` only contains 0, 1, and 2
    fun sortColors(nums: IntArray) {
        var lo = 0
        var hi = nums.size - 1
        var i = 0
        while (i <= hi) {
            when (nums[i]) {
                0 -> {
                    nums.swap(lo, i)
                    lo++
                    i++
                }
                2 -> {
                    nums.swap(i, hi)
                    hi--
                }
                else -> i++
            }
        }
    }

    fun IntArray.swap(i1: Int, i2: Int) {
        val tmp = this[i1]
        this[i1] = this[i2]
        this[i2] = tmp
    }
}