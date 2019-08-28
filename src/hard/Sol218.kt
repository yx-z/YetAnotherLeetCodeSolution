package hard

import java.util.*

class Sol218 {

    fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
        val n = buildings.size
        buildings.sortWith(Comparator { (l0, r0, h0), (l1, r1, h1) ->
            if (r0 == l1) h0.compareTo(h1) else l0.compareTo(l1)
        })
        // min heap
        val heap = PriorityQueue<IntArray>()
        TODO()
    }
}