package median

class Sol1145 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun btreeGameWinningMove(root: TreeNode?, n: Int, x: Int): Boolean {
        var leftX = 0
        var rightX = 0
        fun count(n: TreeNode?): Int {
            if (n == null) return 0
            val l = count(n.left)
            val r = count(n.right)
            if (n.`val` == x) {
                leftX = l
                rightX = r
            }
            return l + r + 1
        }
        count(root)
        return (leftX > n / 2 || rightX > n / 2 || leftX + rightX + 1 < (n + 1) / 2)
    }
}