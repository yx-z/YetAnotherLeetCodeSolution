package median

import java.util.*

class Sol103 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) {
            return emptyList()
        }

        val queue: Queue<Pair<TreeNode, Int>> = LinkedList()
        queue.add(root to 0)
        val res = ArrayList<LinkedList<Int>>()
        while (queue.isNotEmpty()) {
            val (node, level) = queue.remove()
            if (level >= res.size) {
                res.add(LinkedList())
            }
            if (level % 2 == 0) {
                res[level].add(node.`val`)
            } else {
                res[level].add(0, node.`val`)
            }
            if (node.left != null) {
                queue.add(node.left!! to level + 1)
            }
            if (node.right != null) {
                queue.add(node.right!! to level + 1)
            }
        }
        return res
    }
}