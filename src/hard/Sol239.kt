package hard

import java.util.*

class Sol239 {

    // O(n log k)
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

    // O(n)
    fun maxSlidingWindow2(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        if (n == 0 || k > n) {
            return IntArray(0)
        }
        val res = IntArray(n - k + 1)
        var idx = 0
        val dequeue = ArrayDeque<Int>()
        for (i in nums.indices) {
            while (dequeue.isNotEmpty() && dequeue.peekFirst() < i - k + 1) {
                dequeue.removeFirst()
            }
            while (dequeue.isNotEmpty() && nums[dequeue.peekLast()] < nums[i]) {
                dequeue.removeLast()
            }
            dequeue.add(i)
            if (i >= k - 1) {
                res[idx] = nums[dequeue.peekFirst()]
                idx++
            }
        }
        return res
    }
}