package easy

class Sol404 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun sumOfLeftLeaves(root: TreeNode?, isLeft: Boolean = false): Int {
        if (root == null) return 0
        if (root.left == null && root.right == null && isLeft) return root.`val`
        return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right)
    }
}