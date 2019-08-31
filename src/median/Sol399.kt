package median

class Sol399 {

    fun calcEquation(
        eqs: List<List<String>>,
        vs: DoubleArray,
        qs: List<List<String>>
    ): DoubleArray {
        val G = HashMap<String, HashMap<String, Double>>()
        eqs.zip(vs.toList()).forEach { (ls, v) ->
            val (s, e) = ls
            if (s !in G) G[s] = HashMap()
            if (e !in G) G[e] = HashMap()
            G[s]!![s] = 1.0
            G[e]!![e] = 1.0
            G[s]!![e] = v
            G[e]!![s] = 1 / v
        }
        val seen = HashSet<String>()
        fun build(s: String) {
            if (s !in seen) {
                seen.add(s)
                G[s]!!.keys.filterNot { it in seen }.forEach { build(it) }
                G[s]!!.keys.filterNot { it == s }.forEach { e ->
                    G[e]!!.keys.filterNot { it == s }.forEach { o ->
                        G[s]!![o] = G[s]!![e]!! * (G[e]!![o]!!)
                        G[o]!![s] = 1 / G[s]!![o]!!
                    }
                }
            }
        }
        G.keys.forEach { build(it) }
        return qs.map { (s, e) ->
            if (s in G && e in G[s]!!) G[s]!![e]!! else -1.0
        }.toDoubleArray()
    }
}