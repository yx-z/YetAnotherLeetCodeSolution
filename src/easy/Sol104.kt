package easy

import kotlin.math.max

class Sol104 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maxDepth(root: TreeNode?): Int = if (root == null) {
        0
    } else {
        1 + max(maxDepth(root.left), maxDepth(root.right))
    }
}