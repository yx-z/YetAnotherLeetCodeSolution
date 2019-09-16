package hard

import java.util.*

class Sol76 {

    fun minWindow(s: String, t: String): String {
        val target = t.fold(HashMap<Char, Int>()) { m, c ->
            m.apply { m[c] = (m[c] ?: 0) + 1 }
        }
        val curr = HashMap(t.map { it to 0 }.toMap())
        val q = LinkedList<Int>()
        var min = Int.MAX_VALUE
        var l = 0
        var h = 0
        s.indices.filter { s[it] in target }.forEach {
            q.add(it)
            curr[s[it]] = (curr[s[it]] ?: 0) + 1
            while (target.all { (k, v) -> (curr[k] ?: 0) >= v }) {
                if (it - q.peek() < min) {
                    min = it - q.peek()
                    l = q.peek()
                    h = it + 1
                }
                curr[s[q.peek()]] = curr[s[q.peek()]]!! - 1
                q.poll()
            }
        }
        return if (h > l) s.substring(l, h) else ""
    }

    fun redo(s: String, t: String): String {
        val freqT = t.groupingBy { it }.eachCount().toMutableMap()
        var lo = 0
        var hi = 0
        var numCharSatisfied = freqT.size
        var minStart = -1
        var minLength = Int.MAX_VALUE
        while (hi < s.length) {
            val hiChar = s[hi]
            if (hiChar in freqT) {
                freqT[hiChar] = freqT.getValue(hiChar) - 1
                if (freqT[hiChar] == 0) {
                    numCharSatisfied--
                }
            }
            while (numCharSatisfied == 0) {
                val curLen = hi - lo + 1
                if (curLen < minLength) {
                    minLength = curLen
                    minStart = lo
                }
                val loChar = s[lo]
                if (loChar in freqT) {
                    freqT[loChar] = freqT[loChar]!! + 1
                    if (freqT[loChar] == 1) {
                        numCharSatisfied++
                    }
                }
                lo++
            }
            hi++
        }
        return if (minStart == -1) ""
        else s.substring(minStart, minStart + minLength)
    }
}