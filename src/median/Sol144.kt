package median

import java.util.*

class Sol144 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun preorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) {
            return emptyList()
        }

        val ls = ArrayList<Int>()
        val stack = Stack<TreeNode>()
        stack.add(root)
        while (stack.isNotEmpty()) {
            val curr = stack.pop()
            ls.add(curr.`val`)
            if (curr.right != null) {
                stack.add(curr.right)
            }
            if (curr.left != null) {
                stack.add(curr.left)
            }
        }
        return ls
    }
}