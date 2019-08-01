package hard

import java.util.*
import kotlin.math.max

class Sol84 {

    fun largestRectangleArea(hs: IntArray): Int {
        val n = hs.size
        val s = Stack<Int>()
        var i = 0
        var m = 0
        while (i <= n) {
            val h = if (i == n) 0 else hs[i]
            if (s.isEmpty() || h >= hs[s.peek()]) {
                s.push(i)
                i++
            } else {
                val top = s.pop()
                val area = hs[top] * if (s.isEmpty()) i else (i - 1 - s.peek())
                m = max(m, area)
            }
        }
        return m
    }
}