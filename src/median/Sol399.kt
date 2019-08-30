package median

class Sol399 {

    fun calcEquation(
        equations: List<List<String>>,
        values: DoubleArray,
        queries: List<List<String>>
    ): DoubleArray {
        val mem = HashMap<String, HashMap<String, Double>>()
        for (i in equations.indices) {
            val (s, e) = equations[i]
            val v = values[i]
            if (s !in mem) mem[s] = HashMap()
            if (e !in mem) mem[e] = HashMap()
            mem[s]!![s] = 1.0
            mem[e]!![e] = 1.0
            mem[s]!![e] = v
            mem[e]!![s] = 1 / v
        }
        val visited = HashSet<String>()
        fun build(s: String) {
            if (s !in visited) {
                visited.add(s)
                mem[s]!!.keys.filterNot { it in visited }.forEach { build(it) }
                mem[s]!!.keys.filterNot { it == s }.forEach { e ->
                    mem[e]!!.keys.filterNot { it == s }.forEach { o ->
                        mem[s]!![o] = mem[s]!![e]!! * (mem[e]!![o]!!)
                        mem[o]!![s] = 1 / mem[s]!![o]!!
                    }
                }
            }
        }
        for (s in mem.keys) build(s)
        return queries.map { (s, e) ->
            if (s in mem && e in mem[s]!!) mem[s]!![e]!! else -1.0
        }.toDoubleArray()
    }
}