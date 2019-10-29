package Hard

import java.util.*

class Sol219 {

    data class Point(val x: Int, val h: Int, val start: Boolean = true)

    fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
        val points = buildings.flatMap { it.toPoints() }.sortedWith(Comparator
        { p1, p2 ->
            if (p1.x == p2.x)
                (if (p1.start) -p1.h else p1.h) - (if (p2.start) -p2.h else p2.h)
            else (p1.x - p2.x)
        })
        val pq = TreeMap<Int, Int>()
        pq[0] = 1
        var preMax = 0
        val res = ArrayList<List<Int>>()
        points.forEach {
            if (it.start) pq[it.h] = 1 + (pq[it.h] ?: 0)
            else {
                if (pq[it.h] == 1) pq.remove(it.h)
                else pq[it.h] = pq[it.h]!! - 1
            }
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