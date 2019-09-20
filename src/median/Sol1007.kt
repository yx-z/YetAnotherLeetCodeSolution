package median

import kotlin.math.max

class Sol1007 {

    fun minDominoRotations(A: IntArray, B: IntArray): Int {
        val zipped = A.zip(B)
        for (t in 1..6) if (zipped.all { it.first == t || it.second == t })
            return zipped.size - max(A.count { it == t }, B.count { it == t })
        return -1
    }
}