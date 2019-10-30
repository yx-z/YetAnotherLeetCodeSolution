package easy

import java.util.*
import kotlin.collections.HashMap

class Sol392 {

    // O(len(s) + len(t))
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

    // check if s is a subsequence of t
    // assume s is short, t is long
    fun redo(s: String, t: String): Boolean {
        // O(len(t) * log len(t))
        val m = HashMap<Char, TreeSet<Int>>()
        for ((i, c) in t.withIndex()) m.computeIfAbsent(c) { TreeSet() }.add(i)
        var i = -1
        // O(len(s) * log len(t))
        for (c in s) {
            if (c !in m) return false
            val indices = m.getValue(c)
            i = indices.higher(i) ?: return false
            if (i > indices.last()) return false
        }
        return true
    }
}

