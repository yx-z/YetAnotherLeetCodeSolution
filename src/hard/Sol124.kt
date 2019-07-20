package hard

import kotlin.math.max

class Sol124 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    var max = Int.MIN_VALUE

    fun computePath(root: TreeNode): Int {
        var left = 0
        if (root.left != null) {
            left = max(0, computePath(root.left!!))
        }
        var right = 0
        if (root.right != null) {
            right = max(0, computePath(root.right!!))
        }
        max = max(max, root.`val` + left + right)
        return root.`val` + max(left, right)
    }

    fun maxPathSum(root: TreeNode): Int {
        computePath(root)
        return max
    }
}