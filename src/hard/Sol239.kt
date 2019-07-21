package hard

import java.util.*

class Sol239 {

    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        if (n == 0 || k > n) return IntArray(0)
        val res = ArrayList<Int>()
        val heap = PriorityQueue<Int>(Comparator { a, b -> a - b })
        for (i in 0 until k) heap.add(nums[i])
        res.add(heap.peek())
        for (i in k until n) {
            heap.remove(nums[i - k])
            heap.add(nums[i])
            res.add(heap.peek())
        }
        return res.toIntArray()
    }
}