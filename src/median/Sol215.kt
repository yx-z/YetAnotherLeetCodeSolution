package median

import java.util.*

class Sol215 {

    // quickselect O(n)
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val part = { s: Int, e: Int ->
            var s = s
            var e = e
            val p = s
            val swap = { i: Int, j: Int ->
                val tmp = nums[i]
                nums[i] = nums[j]
                nums[j] = tmp
            }
            while (s <= e) {
                while (s <= e && nums[s] <= nums[p]) s++
                while (s <= e && nums[e] > nums[p]) e--
                if (s > e) break
                swap(s, e)
            }
            swap(e, p)
            e
        }
        var s = 0
        var e = nums.size - 1
        val i = nums.size - k
        while (s < e) {
            val p = part(s, e)
            when {
                p < i -> s = p + 1
                p > i -> e = p - 1
                else -> return nums[p]
            }
        }
        return nums[s]
    }

    // heap O(n log k)
    fun findKthLargestHeap(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Int>(k + 1)
        nums.forEach {
            pq.add(it)
            if (pq.size > k) pq.remove()
        }
        return pq.peek()
    }
}