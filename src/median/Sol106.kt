package median

class Sol106 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        val post = postorder.toMutableList()
        val idx = inorder.withIndex().map { it.value to it.index }.toMap()
        fun build(l: Int, h: Int): TreeNode? {
            if (l > h) return null
            val root = TreeNode(post.removeAt(post.lastIndex))
            val mid = idx.getValue(root.`val`)
            root.right = build(mid + 1, h)
            root.left = build(l, mid - 1)
            return root
        }
        return build(0, inorder.size - 1)
    }
}