package easy

class Sol199 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    // it's just a special case of level order traversal
    fun rightSideView(root: TreeNode?) = levelOrder(root).map { it.last() }

    fun levelOrder(
        root: TreeNode?,
        level: Int = 0,
        res: ArrayList<ArrayList<Int>> = ArrayList()
    ): List<List<Int>> {
        if (root == null) {
            return res
        }
        if (res.size == level) {
            res.add(ArrayList())
        }
        res[level].add(root.`val`)
        levelOrder(root.left, level + 1, res)
        levelOrder(root.right, level + 1, res)
        return res
    }
}