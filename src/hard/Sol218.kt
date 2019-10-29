package hard

import java.util.*

class Sol219 {

    data class Point(val x: Int, val h: Int, val isStart: Boolean = true)

    fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
        val points = buildings.flatMap { it.toPoints() }
            .sortedWith(Comparator { p1, p2 ->
                // same type (entering): height descending
                // same type (leaving): height ascending
                // different type but same height: enter first
                // otherwise: x ascending
                if (p1.x != p2.x) (p1.x - p2.x)
                else (if (p1.isStart) -p1.h else p1.h) -
                        (if (p2.isStart) -p2.h else p2.h)
            })
        // <height, # of points at the height>
        val pq = TreeMap<Int, Int>()
        pq[0] = 1
        var preMax = 0
        val res = ArrayList<List<Int>>()
        points.forEach {
            // update # of points based on the type of point and it.h as key
            if (it.isStart) pq[it.h] = 1 + (pq[it.h] ?: 0)
            else {
                if (pq[it.h] == 1) pq.remove(it.h)
                else pq[it.h] = pq[it.h]!! - 1
            }
            // record to res once max height changes (go up/down)
            val curMax = pq.lastKey()
            if (preMax != curMax) {
                res.add(listOf(it.x, curMax))
                preMax = curMax
            }
        }
        return res
    }

    fun IntArray.toPoints() =
        listOf(Point(this[0], this[2]), Point(this[1], this[2], false))
}