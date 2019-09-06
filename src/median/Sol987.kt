package median

import java.util.*

class Sol987 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        val map = TreeMap<Int, TreeMap<Int, PriorityQueue<Int>>>()
        fun dfs(n: TreeNode, x: Int = 0, y: Int = 0) {
            if (x !in map) map[x] = TreeMap()
            val xs = map[x]!!
            if (y !in xs) xs[y] = PriorityQueue()
            xs[y]!!.add(n.`val`)
            if (n.left != null) dfs(n.left!!, x - 1, y + 1)
            if (n.right != null) dfs(n.right!!, x + 1, y + 1)
        }
        dfs(root)
        val res = ArrayList<ArrayList<Int>>()
        map.values.forEach {
            res.add(ArrayList())
            it.values.forEach {
                while (it.isNotEmpty()) res.last().add(it.remove())
            }
        }
        return res
    }
}