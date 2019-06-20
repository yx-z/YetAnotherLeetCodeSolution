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
}