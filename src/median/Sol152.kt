package median

class Sol152 {

    fun maxProduct(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }

        val l = nums.size
        val max = Array(l) { 0 }
        max[l - 1] = nums[l - 1]
        val min = Array(l) { 0 }
        min[l - 1] = nums[l - 1]

        for (i in l - 2 downTo 0) {
            // we see that max/min[i] only depends on max/min[i + 1]
            // so we can use O(1) space tracking next max/min only
            // left as exercise
            max[i] = max(nums[i], nums[i] * max[i + 1], nums[i] * min[i + 1])
            min[i] = min(nums[i], nums[i] * max[i + 1], nums[i] * min[i + 1])
        }

        return max.max()!!
    }


    fun <T : Comparable<T>> max(vararg ts: T) = ts.max()
        ?: throw NoSuchElementException("no max value")

    fun <T : Comparable<T>> min(vararg ts: T) = ts.min()
        ?: throw NoSuchElementException("no min value")
}