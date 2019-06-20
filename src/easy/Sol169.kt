package easy

// all of these are O(n) solutions
class Sol169 {
    // voting algo
    fun majorityElement1(nums: IntArray): Int {
        var majorElem = nums[0]
        var count = 0
        for (n in nums) {
            if (n == majorElem) {
                count++
            } else {
                count--
                if (count == 0) {
                    majorElem = n
                    count = 1
                }
            }
        }
        return majorElem
    }

    // median is the major elem
    // find median in O(n) via quick select
    fun majorityElement2(
        nums: IntArray,
        lo: Int = 0,
        hi: Int = nums.size - 1
    ): Int {
        if (lo == hi) {
            return nums[lo]
        }

        val k = nums.size / 2
        val r = nums.partition(1, lo, hi)
        return when {
            k < r -> majorityElement2(nums, lo, r - 1)
            k > r -> majorityElement2(nums, r + 1, hi)
            else -> nums[r]
        }
    }

    /**
     * partition the array[lo .. hi] so that this[idx] is now at the correct index
     * and return the correct index
     */
    private fun IntArray.partition(idx: Int, lo: Int, hi: Int): Int {
        swap(idx, hi)
        var i = lo
        var j = hi
        while (i < j) {
            do {
                i++
            } while (i < j && this[i] <= this[hi])

            do {
                j--
            } while (i < j && this[j] > this[hi])

            if (i < j) {
                swap(i, j)
            }
        }

        swap(i, hi)
        return i
    }

    private fun IntArray.swap(idx1: Int, idx2: Int) {
        val tmp = this[idx1]
        this[idx1] = this[idx2]
        this[idx2] = tmp
    }
}

// sol3: use map<num, count>
