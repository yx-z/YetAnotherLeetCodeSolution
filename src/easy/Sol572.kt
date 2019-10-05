package easy

class Sol572 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isSubtree(s: TreeNode?, t: TreeNode?): Boolean {
        if (s == null) return t == null
        if (isSameTree(s, t)) return true
        return isSubtree(s.left, t) || isSubtree(s.right, t)
    }

    // from Sol100
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        if (p.`val` != q.`val`) return false
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }
}