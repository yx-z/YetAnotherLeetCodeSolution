package hard

class Sol1163 {

    fun lastSubstring(s: String): String {
        val ints = s.map { it - 'a' }
        val maxStart = ints.max()!!
        val candidates = ints.indices.filter { ints[it] == maxStart }
            .associateWith { it + 1 }.toMutableMap()
        while (candidates.size >= 2) {
            candidates.filterValues { it in candidates }
                .forEach { (_, v) -> candidates.remove(v) }
            candidates.filterValues { it == s.length }
                .forEach { (k, _) -> candidates.remove(k) }
            val max = candidates.map { (_, v) -> ints[v] }.max()!!
            candidates.filterValues { ints[it] < max }
                .forEach { (k, _) -> candidates.remove(k) }
            candidates.forEach { (k, v) -> candidates[k] = v + 1 }
        }
        return s.substring(candidates.keys.first())
    }
}