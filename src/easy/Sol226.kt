package easy

class Sol226 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }

        val l = invertTree(root.left)
        val r = invertTree(root.right)
        root.left = r
        root.right = l
        return root
    }
}