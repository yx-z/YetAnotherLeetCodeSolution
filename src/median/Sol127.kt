package median

import java.util.*
import kotlin.math.min

class Sol127 {

    // G = (V = {beg} UNION dic, E = {(u, v) : d(u, v) == 1})
    // return G.bfs(beg, end)
    fun ladderLength(beg: String, end: String, dic: List<String>): Int {
        val v = dic.toHashSet().apply { add(beg) }
        if (end !in v) return 0
        val d = { a: String, b: String -> a.indices.count { a[it] != b[it] } }
        val e = v.map { u -> u to v.filter { d(u, it) == 1 } }.toMap()
        val l = hashMapOf(beg to 0)
        val s = HashSet<String>()
        val q: Queue<String> = LinkedList()
        q.offer(beg)
        while (q.isNotEmpty()) {
            val u = q.poll()
            s.add(u)
            e.getValue(u).filter { it !in s }.forEach {
                l[it] = min(l[it] ?: Int.MAX_VALUE, 1 + l[u]!!)
                q.add(it)
            }
        }
        return 1 + (l[end] ?: -1)
    }
}