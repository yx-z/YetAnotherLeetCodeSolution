package median

import kotlin.math.abs

class Sol979 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun distributeCoins(root: TreeNode?): Int {
        var moves = 0
        fun buildNeed(n: TreeNode?): Int {
            if (n == null) return 0
            val l = buildNeed(n.left)
            val r = buildNeed(n.right)
            moves += abs(l) + abs(r)
            return n.`val` - 1 + l + r
        }
        buildNeed(root)
        return moves
    }
}