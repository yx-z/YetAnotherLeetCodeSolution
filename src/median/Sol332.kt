package median

import java.util.*

class Sol332 {

    fun findItinerary(tickets: List<List<String>>): List<String> {
        val map = HashMap<String, PriorityQueue<String>>()
        tickets.forEach { (from, to) ->
            map.computeIfAbsent(from) { PriorityQueue() }.add(to)
        }
        val EMPTY = PriorityQueue<String>()
        val route = LinkedList<String>()
        fun visit(s: String) {
            while ((map[s] ?: EMPTY).isNotEmpty()) visit(map[s]!!.poll())
            route.addFirst(s)
        }
        visit("JFK")
        return route
    }


    operator fun List<String>.component1() = this[0]
    operator fun List<String>.component2() = this[1]
}