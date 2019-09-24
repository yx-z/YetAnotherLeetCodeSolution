package median

import kotlin.math.max

class Sol3 {

    fun lengthOfLongestSubstring(s: String): Int {
        val seen = HashSet<Char>()
        var lo = 0
        var hi = 0
        var longest = 0
        while (hi < s.length) {
            if (seen.contains(s[hi])) {
                seen.remove(s[lo])
                lo++
            } else {
                seen.add(s[hi])
                longest = max(longest, seen.size)
                hi++
            }
        }
        return longest
    }

    fun redo(s: String): Int {
        val seen = HashMap<Char, Int>()
        var lo = 0
        var hi = 0
        var longest = 0
        while (hi < s.length) {
            val hiChar = s[hi]
            if (hiChar in seen && seen[hiChar]!! >= lo) {
                lo = seen[hiChar]!! + 1
            } else {
                longest = max(longest, hi - lo + 1)
            }
            seen[hiChar] = hi
            hi++
        }
        return longest
    }
}