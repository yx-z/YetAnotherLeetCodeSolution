package median

import kotlin.math.min

class Sol986 {

    fun intervalIntersection(
        A: Array<IntArray>,
        B: Array<IntArray>
    ): Array<IntArray> {
        val res = ArrayList<IntArray>()
        var a = 0
        var b = 0
        while (a < A.size && b < B.size) {
            val (a1, a2) = A[a]
            val (b1, b2) = B[b]
            when {
                a1 in b1..b2 -> res.add(intArrayOf(a1, min(a2, b2)))
                b1 in a1..a2 -> res.add(intArrayOf(b1, min(a2, b2)))
            }
            if (a2 < b2) a++ else b++
        }
        return res.toTypedArray()
    }

    fun IntArray.component1() = this[0]
    fun IntArray.component2() = this[1]
}