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

    // morris traversal
    fun redo(root: TreeNode?): List<Int> {
        val res = ArrayList<Int>()
        var curr = root
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.`val`)
                curr = curr.right
            } else {
                var pre = curr.left!!
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right!!
                }
                if (pre.right == null) {
                    pre.right = curr
                    curr = curr.left // go left
                } else {
                    // pre.right == curr
                    pre.right = null // restore original tree structure
                    res.add(curr.`val`)
                    curr = curr.right // go right
                }
            }
        }
        return res
    }
}