package hard

import java.util.*

class Sol642 {

    class Trie(
        val next: HashMap<Char, Trie> = HashMap(),
        val freq: HashMap<String, Int> = HashMap()
    )

    class AutocompleteSystem(sentences: Array<String>, times: IntArray) {
        private val substr = StringBuilder()
        private val root = Trie()

        init {
            sentences.indices.forEach { add(sentences[it], times[it]) }
        }


        fun input(c: Char) = when (c) {
            in 'a'..'z', ' ' -> {
                substr.append(c)
                var curr = root
                substr.forEach {
                    curr = curr.next[it] ?: return emptyList<String>()
                }
                val pq = PriorityQueue<Map.Entry<String, Int>>(
                    Comparator { (s1, c1), (s2, c2) ->
                        if (c1 == c2) s1.compareTo(s2) else c1.compareTo(c2)
                    })
                curr.freq.forEach { pq.add(it) }
                val res = ArrayList<String>()
                for (i in 1..3) {
                    if (pq.isNotEmpty()) {
                        res.add(pq.poll().key)
                    }
                }
                res
            }
            else -> {
                add(substr.toString())
                substr.clear()
                emptyList<String>()
            }
        }

        fun add(word: String, count: Int = 1) {
            var curr = root
            word.forEach {
                curr = curr.next[it] ?: Trie().apply { curr.next[it] = this }
                curr.freq[word] = count + (curr.freq[word] ?: 0)
            }
        }
    }
}