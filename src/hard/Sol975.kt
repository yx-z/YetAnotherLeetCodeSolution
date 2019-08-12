package hard

import java.util.*

class Sol975 {

    fun getNext(order: List<Int>): IntArray {
        val res = IntArray(order.size) { -1 }
        val stack = Stack<Int>()
        for (i in order) {
            while (stack.isNotEmpty() && i > stack.peek()) {
                res[stack.pop()] = i
            }
            stack.push(i)
        }
        return res
    }

    fun oddEvenJumps(A: IntArray): Int {
        val n = A.size
        val oddNext = getNext(A.indices.sortedBy { A[it] })
        val evenNext = getNext(A.indices.sortedBy { -A[it] })
        val even = BooleanArray(n).apply { this[n - 1] = true }
        val odd = BooleanArray(n).apply { this[n - 1] = true }
        for (i in n - 2 downTo 0) {
            if (oddNext[i] != -1) {
                odd[i] = even[oddNext[i]]
            }
            if (evenNext[i] != -1) {
                even[i] = odd[evenNext[i]]
            }
        }
        return odd.count { it }
    }
}
