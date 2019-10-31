package hard

import kotlin.math.min

class Sol1192 {
    fun criticalConnections(
        n: Int,
        connections: List<List<Int>>
    ): List<List<Int>> {
        val disc = IntArray(n) { -1 }
        val low = IntArray(n)
        val graph = Array(n) { ArrayList<Int>() }
        val res = ArrayList<List<Int>>()
        connections.forEach { (u, v) ->
            graph[u].add(v)
            graph[v].add(u)
        }

        (0 until n).forEach {
            if (disc[it] == -1)
                dfs(it, it, low, disc, graph, res)
        }
        return res
    }

    var time = 0
    fun dfs(
        u: Int, pre: Int, low: IntArray, disc: IntArray,
        graph: Array<ArrayList<Int>>, res: ArrayList<List<Int>>
    ) {
        time++
        low[u] = time
        disc[u] = low[u] // discover u
        graph[u].forEach { v ->
            if (v != pre) {
                if (disc[v] == -1) { // if not discovered
                    dfs(v, u, low, disc, graph, res)
                    low[u] = min(low[u], low[v])
                    // u - v is critical, there is no path for v to reach back to u or previous vertices of u
                    if (low[v] > disc[u]) res.add(listOf(u, v))
                } else { // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
                    low[u] = min(low[u], disc[v])
                }
            }
        }
    }
}