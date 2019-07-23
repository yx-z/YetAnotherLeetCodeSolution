package median

import java.util.*

class Sol973 {

    fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
        val dist = { p: IntArray -> p[0] * p[0] + p[1] * p[1] }
        val pq = PriorityQueue<IntArray>(K, Comparator { p, q ->
            dist(p) - dist(q)
        }).apply { points.forEach { add(it) } }
        return (1..K).map { pq.remove() }.toTypedArray()
    }
}