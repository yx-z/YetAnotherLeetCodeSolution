package median

import java.util.*

class Sol973 {

    fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
        val res = arrayOfNulls<IntArray>(K)
        val pq = PriorityQueue<IntArray>(K, Comparator { p1, p2 ->
            p1.dist() - p2.dist()
        })
        points.forEach { pq.add(it) }
        for (i in 0 until K) res[i] = pq.remove()
        return res.requireNoNulls()
    }

    fun IntArray.dist() = this[0] * this[0] + this[1] * this[1]
}