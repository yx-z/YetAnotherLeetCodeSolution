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
}