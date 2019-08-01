package median

import java.util.*

class Sol692 {

    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val freq = words.fold(HashMap<String, Int>()) { map, word ->
            map[word] = 1 + (map[word] ?: 0)
            map
        }
        val top = PriorityQueue<Map.Entry<String, Int>>(Comparator
        { (s1, f1), (s2, f2) -> if (f1 == f2) s1.compareTo(s2) else f2 - f1 })
            .apply { addAll(freq.entries) }
        return (1..k).map { top.poll().key }
    }
}