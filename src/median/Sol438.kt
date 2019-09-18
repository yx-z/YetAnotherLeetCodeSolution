package median

class Sol438 {

    fun findAnagrams(s: String, p: String): List<Int> {
        val res = ArrayList<Int>()
        if (s.isEmpty() || p.isEmpty()) return res
        val freqP = p.groupingBy { it }.eachCount().toMutableMap()
        var lo = 0
        var hi = 0
        var count = 0 // # of chars satisfied in target
        while (hi < s.length) {
            val curHi = s[hi]
            if (curHi in freqP) {
                freqP[curHi] = freqP[curHi]!! - 1
                if (freqP[curHi] == 0) {
                    count++
                }
                if (count == freqP.size) {
                    res.add(lo)
                }
            }
            if (hi - lo + 1 == p.length) {
                val curLo = s[lo]
                if (curLo in freqP) {
                    if (freqP[curLo] == 0) count--
                    freqP[curLo] = freqP[curLo]!! + 1
                }
                lo++
            }
            hi++
        }
        return res
    }
}