package median

import kotlin.math.max

class Sol56 {

    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.isEmpty()) {
            return emptyArray()
        }

        intervals.sortWith(Comparator { i1, i2 ->
            if (i1[0] == i2[0]) {
                i1[1] - i2[1]
            } else {
                i1[0] - i2[0]
            }
        })

        val ls = arrayListOf(intervals[0])
        for (int in intervals) {
            val last = ls.last()
            if (int[0] > last[1]) {
                ls.add(int)
            } else {
                last[1] = max(last[1], int[1])
            }
        }
        return ls.toTypedArray()
    }
}