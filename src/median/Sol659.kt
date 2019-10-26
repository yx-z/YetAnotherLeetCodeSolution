package median

import kotlin.math.max
import kotlin.math.min

class Sol659 {

    fun isPossible(ns: IntArray): Boolean {
        val freq = ns.toList().groupingBy { it }.eachCount().toMutableMap()
        val extend = HashMap<Int, Int>()
        ns.forEach {
            if (freq[it]!! > 0) {
                when {
                    extend[it] ?: 0 > 0 -> {
                        extend[it] = extend[it]!! - 1
                        extend[it + 1] = (extend[it + 1] ?: 0) + 1
                    }
                    (freq[it + 1] ?: 0 > 0) && (freq[it + 2] ?: 0 > 0) -> {
                        freq[it + 1] = freq[it + 1]!! - 1
                        freq[it + 2] = freq[it + 2]!! - 1
                        extend[it + 3] = (extend[it + 3] ?: 0) + 1
                    }
                    else -> return false
                }
                freq[it] = freq[it]!! - 1
            }
        }
        return true
    }

    fun redo(ns: IntArray): Boolean {
        val n = ns.size
        if (n < 3) return false
        var preLen1 = 0
        var preLen2 = 0
        var preLen3 = 0
        var curLen1: Int
        var curLen2: Int
        var curLen3: Int
        var curNum: Int
        var preNum = Int.MIN_VALUE
        var i = 0
        while (i < n) {
            curNum = ns[i]
            var count = 0
            while (i < n && ns[i] == curNum) {
                count++
                i++
            }
            if (preNum + 1 != curNum) {
                if (preLen1 != 0 || preLen2 != 0) return false
                curLen1 = count
                curLen2 = 0
                curLen3 = 0
            } else {
                // preNum + 1 == curNum
                if (count < preLen1 + preLen2) return false
                curLen2 = preLen1
                curLen3 = preLen2
                val residual = count - preLen1 - preLen2
                val extendL3 = min(preLen3, residual)
                curLen3 += extendL3
                curLen1 = max(0, residual - extendL3)
            }
            preNum = curNum
            preLen1 = curLen1
            preLen2 = curLen2
            preLen3 = curLen3
        }
        return preLen1 == 0 && preLen2 == 0
    }
}