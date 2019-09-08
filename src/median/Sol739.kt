package median

import java.util.*

class Sol739 {

    fun dailyTemperatures(T: IntArray): IntArray {
        val n = T.size
        val res = IntArray(n)
        val s = Stack<Int>()
        for ((i, t) in T.withIndex()) {
            while (s.isNotEmpty() && T[s.peek()] < t) {
                val last = s.pop()
                res[last] = i - last
            }
            s.add(i)
        }
        return res
    }
}