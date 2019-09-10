package hard

class Sol780 {

    fun reachingPoints(sx: Int, sy: Int, tx: Int, ty: Int): Boolean {
        if (sx > tx || sy > ty) return false
        // ty = sy + k * sx, for some k
        if (sx == tx && (ty - sy) % sx == 0) return true
        if (sy == ty && (tx - sx) % sy == 0) return true
        // if tx > ty, and we know tx, ty >= 1
        // we must come from (tx - ty, tx) since (tx, ty - tx) has ty - tx < 0
        // now if tx - ty > ty, the condition is still the same
        // we must come from (tx - ty - ty, ty)
        // thus eventually we come from (tx % ty, ty)
        return if (tx > ty) reachingPoints(sx, sy, tx % ty, ty)
        else reachingPoints(sx, sy, tx, ty % tx)
    }
}
