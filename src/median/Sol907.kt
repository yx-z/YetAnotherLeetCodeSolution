package median

import java.util.*

class Sol907 {
    val MOD = 1000000007

    fun sumSubarrayMins(A: IntArray): Int {
        val n = A.size
        val pre = IntArray(n)
        val preStack = Stack<Int>()
        for (i in A.indices) {
            while (preStack.isNotEmpty() && A[i] <= A[preStack.peek()]) {
                preStack.pop()
            }
            pre[i] = if (preStack.isEmpty()) -1 else preStack.peek()
            preStack.push(i)
        }
        val nex = IntArray(n)
        val nexStack = Stack<Int>()
        for (i in n - 1 downTo 0) {
            while (nexStack.isNotEmpty() && A[i] < A[nexStack.peek()]) {
                nexStack.pop()
            }
            nex[i] = if (nexStack.isEmpty()) n else nexStack.peek()
            nexStack.push(i)
        }
        var res = 0L
        for (i in A.indices) {
            res += (i - pre[i]) * (nex[i] - i) % MOD * A[i] % MOD
            res %= MOD
        }
        return res.toInt()
    }
}