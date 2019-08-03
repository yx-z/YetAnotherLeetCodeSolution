package hard

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println(
        Sol126().findLadders(
            "hit", "cog",
            listOf("hot", "dot", "dog", "lot", "log", "cog")
        )
    )
}

class Sol126 {

    fun findLadders(
        beg: String,
        end: String,
        ws: List<String>
    ): List<List<String>> {
        class Vert(
            val s: String,
            val m: HashMap<Int, ArrayList<Vert>> = HashMap()
        )

        val str2v = HashMap<String, Vert>()
        val V = (ws + beg).map { w -> Vert(w).also { str2v[w] = it } }.toSet()
        if (end !in str2v.keys) return emptyList()
        val startVertex = str2v[beg]!!
        startVertex.m[0] = arrayListOf(startVertex)

        val d = { a: String, b: String -> a.indices.count { a[it] != b[it] } }
        val E = V.map { u -> u to V.filter { d(u.s, it.s) == 1 } }.toMap()

        val queue: Queue<Vert> = LinkedList()
        queue.add(startVertex)
        val seen = HashSet<Vert>()
        while (queue.isNotEmpty()) {
            val u = queue.remove()
            if (u !in seen) {
                seen.add(u)
                val m = u.m.keys.min()!!
                E.getValue(u).forEach {
                    if (m + 1 !in it.m.keys) {
                        it.m[m + 1] = ArrayList()
                    }
                    it.m[m + 1]!!.add(u)
                    queue.add(it)
                }
            }
        }

        val endVertex = str2v[end]!!
        if (endVertex !in seen) {
            return emptyList()
        }
        fun f(endV: Vert): List<LinkedList<String>> {
            if (endV == startVertex) {
                return arrayListOf(LinkedList<String>().apply { add(endV.s) })
            }
            val m = endV.m.keys.min()!!
            return endV.m[m]!!.flatMap { f(it).map { it.apply { add(endV.s) } } }
        }
        return f(endVertex)
    }
}