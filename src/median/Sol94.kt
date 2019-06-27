package median

import java.util.*

class Sol94 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    // no recursion allowed
    fun inorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) {
            return emptyList()
        }
        val res = ArrayList<Int>()
        val stack = Stack<TreeNode>()
        var curr = root
        while (stack.isNotEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr)
                curr = curr.left
            }
            curr = stack.pop()
            res.add(curr.`val`)
            curr = curr.right
        }
        return res
    }
}