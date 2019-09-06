package hard

import java.util.*

class Sol295 {

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * var obj = MedianFinder()
     * obj.addNum(num)
     * var param_2 = obj.findMedian()
     */
    class MedianFinder {

        /** initialize your data structure here. */
        // min heap for A[n/2+1..n]
        val max = PriorityQueue<Int>()
        // max heap for A[1..n/2]
        val min = PriorityQueue<Int>(Collections.reverseOrder())
        val even: Boolean get() = (min.size + max.size) % 2 == 0

        fun addNum(num: Int) {
            if (even) {
                max.offer(num)
                min.offer(max.poll())
            } else {
                min.offer(num)
                max.offer(min.poll())
            }
        }

        fun findMedian() = if (even) (min.peek() + max.peek()) / 2.toDouble()
        else min.peek().toDouble()
    }
}