package median

import kotlin.math.abs

class Sol442 {

    fun findDuplicates(ns: IntArray): List<Int> {
        val res = ArrayList<Int>()
        for (n in ns) {
            val m = abs(n)
            if (ns[m - 1] > 0) ns[m - 1] = -ns[m - 1]
            else res.add(m)
        }
        return res
    }
}