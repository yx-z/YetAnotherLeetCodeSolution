package median

import java.util.*

class Sol332 {

    fun findItinerary(ts: List<List<String>>): List<String> {
        val map = HashMap<String, PriorityQueue<String>>()
        ts.forEach { map.computeIfAbsent(it[0]) { PriorityQueue() }.add(it[1]) }
        val route = LinkedList<String>()
        fun dfs(s: String) {
            while (s in map && map[s]!!.isNotEmpty()) dfs(map[s]!!.poll())
            route.addFirst(s)
        }
        dfs("JFK")
        return route
    }
}