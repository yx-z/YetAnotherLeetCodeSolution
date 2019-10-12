package easy

import kotlin.math.max

class Sol543 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var diam = 0
        fun longestAt(n: TreeNode? = root): Int {
            if (n == null) {
                return 0
            }
            val l = longestAt(n.left)
            val r = longestAt(n.right)
            diam = max(diam, l + r)
            return 1 + max(l, r)
        }
        longestAt()
        return diam
    }
}