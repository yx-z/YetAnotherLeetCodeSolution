package median

import kotlin.math.max
import kotlin.math.min

class Sol223 {

    fun computeArea(
        A: Int,
        B: Int,
        C: Int,
        D: Int,
        E: Int,
        F: Int,
        G: Int,
        H: Int
    ): Int {
        if (A > E) return computeArea(E, F, G, H, A, B, C, D)
        // A <= E, i.e. rect1 is to the left of rect2
        val rect1 = (C - A) * (D - B)
        val rect2 = (G - E) * (H - F)
        val sum = rect1 + rect2
        if (E >= C) return sum // rect2 is too right
        if (B >= H || D <= F) return sum
        val overlapWidth = min(C, G) - max(A, E)
        val overlapHeight = min(D, H) - max(B, F)
        return sum - overlapWidth * overlapHeight
    }
}
