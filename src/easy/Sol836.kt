package easy

class Sol836 {

    fun isRectangleOverlap(rec1: IntArray, rec2: IntArray): Boolean {
        val (l1, b1, r1, t1) = rec1
        val (l2, b2, r2, t2) = rec2
        if (l1 > l2) return isRectangleOverlap(rec2, rec1)
        // l1 <= l2
        val ys1 = b1 + 1 until t1
        val ys2 = b2 + 1 until t2
        return l2 < r1 && (t2 in ys1 || b2 in ys1 || t1 in ys2 || b1 in ys2)
    }

    fun redo(rec1: IntArray, rec2: IntArray): Boolean {
        val (l1, b1, r1, t1) = rec1
        val (l2, b2, r2, t2) = rec2
        // negate all non-overlapping cases of [rec1 is to the XXX of rec2]
        return !(r1 <= l2 || // XXX = left
                b1 >= t2 || // XXX = top
                l1 >= r2 || // XXX = right
                t1 <= b2) // XXX = bottom
    }
}