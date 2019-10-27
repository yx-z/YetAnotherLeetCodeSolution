package hard

import java.util.*

class Sol145 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun postorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        val res = ArrayList<Int>()
        val stack = Stack<TreeNode>()
        stack.add(root)
        while (stack.isNotEmpty()) {
            val curr = stack.pop()
            res.add(curr.`val`)
            if (curr.left != null) stack.add(curr.left)
            if (curr.right != null) stack.add(curr.right)
        }
        return res.reversed()
    }

    fun redo(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        var curr = root
        val res = ArrayList<Int>()
        val stack = Stack<TreeNode>()
        while (true) {
            while (curr != null) {
                stack.push(curr)
                stack.push(curr)
                curr = curr.left
            }
            if (stack.isEmpty()) return res
            curr = stack.pop()
            curr = if (stack.isNotEmpty() && stack.peek() == curr) {
                curr.right
            } else {
                res.add(curr.`val`)
                null
            }
        }
    }
}