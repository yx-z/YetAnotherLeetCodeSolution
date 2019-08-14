package median

import java.util.*
import kotlin.random.Random

class Sol528(w: IntArray) {

    val l = w.size
    val r = Random(System.currentTimeMillis())
    val weight = IntArray(l).apply {
        this[0] = w[0]
        for (i in 1 until l) {
            this[i] = this[i - 1] + w[i]
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * var obj = Solution(w)
     * var param_1 = obj.pickIndex()
     */
    fun pickIndex(): Int {
        val w = r.nextInt(weight.last()) + 1
        val i = Arrays.binarySearch(weight, w)
        return if (i >= 0) i else -i - 1
    }
}