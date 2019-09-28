package median

import java.util.*
import kotlin.math.max
import kotlin.math.min

class Sol1130 {

    // dp
    // O(n^3) time
    // O(n^2) space
    fun mctFromLeafValues(arr: IntArray): Int {
        val n = arr.size
        // max[i, j] = max(arr[i..j])
        val max = Array(n) { IntArray(n) }
        for (i in 0 until n) max[i][i] = arr[i]
        for (i in 0 until n) for (j in i + 1 until n)
            max[i][j] = max(max[i][j - 1], arr[j])
        // dp[i, j] = min cost for arr[i..j] : j >= i
        val dp = Array(n) { IntArray(n) }
        for (i in 0 until n - 1) dp[i][i + 1] = arr[i] * arr[i + 1]
        for (i in n - 1 downTo 0) for (j in i + 2 until n)
            dp[i][j] = (i + 1..j).map { k ->
                dp[i][k - 1] + dp[k][j] + max[i][k - 1] * max[k][j]
            }.min()!!
        return dp[0][n - 1]
    }

    // greedy
    // O(n^2) time
    // O(1) space
    fun redo(arr: IntArray): Int {
        val ls = arr.toMutableList()
        var res = 0
        while (ls.size >= 2) {
            val minIdx = ls.indices.minBy { ls[it] }!!
            res += ls[minIdx] * when (minIdx) {
                0 -> ls[1]
                ls.size - 1 -> ls[minIdx - 1]
                else -> min(ls[minIdx - 1], ls[minIdx + 1])
            }
            ls.removeAt(minIdx)
        }
        return res
    }

    // stack
    // O(n) time
    // O(n) space
    fun redo2(arr: IntArray): Int {
        val s = Stack<Int>().apply { add(Int.MAX_VALUE) }
        var res = 0
        arr.forEach {
            while (s.isNotEmpty() && s.peek() <= it)
                res += s.pop() * min(s.peek(), it)
            s.add(it)
        }
        while (s.size > 2) res += s.pop() * s.peek()
        return res
    }
}