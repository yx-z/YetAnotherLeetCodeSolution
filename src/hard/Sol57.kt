package hard

import kotlin.math.max
import kotlin.math.min

class Sol57 {

    fun insert(ints: Array<IntArray>, newInt: IntArray): Array<IntArray> {
        val (newStart, newEnd) = newInt
        val merged = intArrayOf(newStart, newEnd)
        ints.filter { newStart in it[0]..it[1] || newEnd in it[0]..it[1] }
            .forEach { (curStart, curEnd) ->
                merged[0] = min(merged[0], curStart)
                merged[1] = max(merged[1], curEnd)
            }
        val left = ints.filter { it[1] < newStart }
        val right = ints.filter { it[0] > newEnd }
        return (left + merged + right).toTypedArray()
    }
}