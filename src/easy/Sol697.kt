package easy

import kotlin.math.min

fun main() {
    println(Sol697().onePass(intArrayOf(1, 2, 2, 3, 1)))
}

class Sol697 {

    fun findShortestSubArray(nums: IntArray): Int {
        val idx = HashMap<Int, ArrayList<Int>>()
        for ((i, n) in nums.withIndex()) {
            if (n !in idx) idx[n] = ArrayList()
            idx[n]!!.add(i)
        }
        val deg = idx.values.map { it.size }.max()!!
        return idx.values.filter { it.size == deg }
            .map { it.last() - it.first() + 1 }.min()!!
    }

    fun onePass(nums: IntArray): Int {
        val freq = HashMap<Int, Int>()
        val first = HashMap<Int, Int>()
        var deg = 0
        var len = 0
        for ((i, n) in nums.withIndex()) {
            freq[n] = (freq[n] ?: 0) + 1
            if (n !in first) first[n] = i
            when {
                freq[n]!! > deg -> {
                    deg = freq[n]!!
                    len = i - first[i]!! + 1
                }
                freq[n]!! == deg -> len = min(len, i - first[i]!! + 1)
            }
        }
        return len
    }
}