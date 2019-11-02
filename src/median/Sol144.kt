package median

import java.util.*
import kotlin.collections.ArrayList

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

    // morris
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
                    res.add(curr.`val`)
                    pre.right = curr
                    curr = curr.left // go left
                } else {
                    // pre.right == curr
                    pre.right = null
                    curr = curr.right // go right
                }
            }
        }
        return res
    }
}