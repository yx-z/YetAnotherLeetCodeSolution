package median

class Sol98 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private val ls = ArrayList<Int>()

    fun isValidBST(root: TreeNode?): Boolean {
        inorder(root)
        return (1 until ls.size).all { ls[it] > ls[it - 1] }
    }

    private fun inorder(root: TreeNode?) {
        if (root != null) {
            inorder(root.left)
            ls.add(root.`val`)
            inorder(root.right)
        }
    }

    fun isValidBST2(
        root: TreeNode?,
        lo: Long = Int.MIN_VALUE - 1L,
        hi: Long = Int.MAX_VALUE + 1L
    ): Boolean {
        if (root == null) return true
        if (root.`val` !in lo + 1 until hi) return false
        return isValidBST2(root.left, lo, root.`val`.toLong()) &&
                isValidBST2(root.right, root.`val`.toLong(), hi)
    }
}