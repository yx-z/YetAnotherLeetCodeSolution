package median

class Sol114 {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun flatten(root: TreeNode?) {
        if (root != null) {
            flatten(root.left)
            flatten(root.right)
            if (root.left != null) {
                var cur = root.left!!
                while (cur.right != null) {
                    cur = cur.right!!
                }
                // cur != null, cur.right == null
                cur.right = root.right
                root.right = root.left
                root.left = null
            }
        }
    }

    fun redo(root: TreeNode?) {
        var cur: TreeNode? = root
        while (cur != null) {
            if (cur.left != null) {
                var pre = cur.left!!
                while (pre.right != null) pre = pre.right!!
                pre.right = cur.right
                cur.right = cur.left
                cur.left = null
            }
            cur = cur.right
        }
    }
}