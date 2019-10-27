package easy

import java.util.*
import kotlin.collections.ArrayList

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

    fun redo(
        root: TreeNode?,
        depth: Int = 0,
        result: ArrayList<Int> = ArrayList()
    ): List<Int> {
        if (root == null) return result
        if (result.size <= depth) result.add(root.`val`)
        redo(root.right, depth + 1, result)
        redo(root.left, depth + 1, result)
        return result
    }

    fun redoLevelOrder(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        val res = ArrayList<Int>()
        val queue = LinkedList<TreeNode>().apply { add(root) }
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 1..size) {
                val node = queue.removeFirst()
                if (i == size) res.add(node.`val`)
                if (node.left != null) queue.add(node.left!!)
                if (node.right != null) queue.add(node.right!!)
            }
        }
        return res
    }
}