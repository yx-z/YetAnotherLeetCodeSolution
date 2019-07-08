package median

import java.util.*


class Sol230 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun kthSmallest(root: TreeNode, k: Int) = inorder(root, k)

    fun inorder(root: TreeNode, k: Int): Int {
        val res = ArrayList<Int>()
        val stack = Stack<TreeNode>()
        var curr: TreeNode? = root
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr)
                curr = curr.left
            }
            curr = stack.pop()
            res.add(curr!!.`val`)
            if (res.size == k) {
                return res[k - 1]
            }
            curr = curr.right
        }
        return -1
    }
}