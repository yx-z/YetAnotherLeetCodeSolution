package easy

class Sol101 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isSymmetric(root: TreeNode?, other: TreeNode? = root): Boolean {
        if (root == null && other == null) return true
        if (root == null || other == null) return false
        if (root.`val` != other.`val`) return false
        return isSymmetric(root.left, other.right) &&
                isSymmetric(root.right, other.left)
    }
}