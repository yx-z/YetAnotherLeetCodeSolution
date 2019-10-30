package median

import java.util.*

class Sol792 {

    // O((len(S) + len(word)) * len(words))
    fun numMatchingSubseq(S: String, words: Array<String>) =
        words.count { isSubsequence(it, S) }
    // if we use redo() in Sol392, and preprocess S that takes
    // O(len(S) * log len(S)), the total time will be
    // O(len(word) * log len(S) * len(words))


    // Sol392
    fun isSubsequence(s: String, t: String): Boolean {
        var si = 0
        var ti = 0
        while (ti < t.length) {
            if (s[si] == t[ti]) {
                si++
                if (si == s.length) return true
            }
            ti++
        }
        return false
    }

    fun redo(S: String, words: Array<String>): Int {
        val wordMap = ('a'..'z').associateWith { LinkedList<String>() }
        words.forEach { wordMap.getValue(it[0]).add(it) }
        var count = 0
        for (c in S) {
            val matchQueue = wordMap.getValue(c)
            val size = matchQueue.size
            repeat(size) {
                val currWord = matchQueue.removeFirst()
                if (currWord.length == 1) count++
                else wordMap.getValue(currWord[1]).addLast(currWord.substring(1))
            }
        }
        return count
    }
}