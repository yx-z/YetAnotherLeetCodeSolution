package easy

import java.util.*

class Sol496 {

    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        val big = HashMap<Int, Int>()
        val pre = Stack<Int>()
        for (n in nums2) {
            while (pre.isNotEmpty() && pre.peek() < n) big[pre.pop()] = n
            pre.push(n)
        }
        val r = IntArray(nums1.size)
        for ((i, n) in nums1.withIndex()) r[i] = if (n in big) big[n]!! else -1
        return r
    }
}