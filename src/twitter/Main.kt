package twitter

import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {

}

fun restock(ints: IntArray, target: Int): Int {
    var cur = 0
    for (i in ints) {
        if (cur + i > target) break
        cur += i
    }
    return abs(cur - target)
}

fun minK(qs: IntArray): Int {
    val n = qs.size
    val scores = qs.map { if (it == 0) -1 else it }
    val sum = IntArray(n).apply { this[0] = scores[0] }
    for (i in 1 until n) sum[i] = sum[i - 1] + scores[i]
    return (1..n).first { 2 * sum[it - 1] > sum[n - 1] }
}

fun toAnagram(a: String, b: String): Int {
    val freq = HashMap<Char, Int>()
    b.forEach { freq[it] = 1 + (freq[it] ?: 0) }
    a.forEach { freq[it] = (freq[it] ?: 0) - 1 }
    val neg = -freq.values.filter { it < 0 }.sum()
    val pos = freq.values.filter { it > 0 }.sum()
    return max(pos, neg)
}

fun minCover(len: Int, water: Array<Pair<Int, Int>>): Int {
    water.sortBy { it.first }
    val grouped = TreeMap<Int, List<Pair<Int, Int>>>(water.groupBy { it.first })
    if (0 !in grouped) return -1
    var last = grouped.getValue(0).last()
    var count = 1
    while (last.second < len) {
        var i = grouped.ceilingKey(last.first + 1)
        var max: Pair<Int, Int>? = null
        while (i != null && i <= last.second) {
            if (max == null || max.second < grouped[i]!!.last().second)
                max = grouped[i]!!.last()
            i = grouped.ceilingKey(i + 1)
        }
        last = max ?: return -1
        count++
    }
    return count
}

fun splitK(A: IntArray, k: Int): Boolean {
    val freq = HashMap<Int, Int>()
    for (a in A) freq[a] = (freq[a] ?: 0) + 1
    return freq.values.all { it == freq[A[0]]!! }
}

fun transform(x1: Int, y1: Int, x2: Int, y2: Int): Int {
    val memo = HashMap<Pair<Int, Int>, Int>()
    fun dfs(x: Int, y: Int): Int {
        val p = x to y
        if (p !in memo) {
            memo[p] = when {
                x == x2 && y == y2 -> 0
                x > x2 || y > y2 -> Int.MAX_VALUE - 1
                else -> 1 + min(dfs(x + y, y), dfs(x, x + y))
            }
            if (memo[p] == Int.MAX_VALUE) memo[p] = Int.MAX_VALUE - 1
        }
        return memo[p]!!
    }
    return dfs(x1, y1)
}

fun maxEvents(arr: IntArray, len: IntArray): Int {
    val events = arr.zip(len).map { (f, s) -> f to f + s }.sortedBy { it.first }
    var count = 0
    var last = 0 to 0
    for (event in events) {
        when {
            event.first >= last.second -> {
                last = event
                count++
            }
            event.second < last.second -> last = event
        }
    }
    return count
}

fun usrId(arr: IntArray): Int {
    val ids = BooleanArray(3001).apply { this[0] = true }
    for (i in arr.indices) {
        if (ids[arr[i]]) arr[i] = ids.indexOfFirst { !it }
        ids[arr[i]] = true
    }
    println(arr.contentToString())
    return arr.sum()
}
