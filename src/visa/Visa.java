package visa;

import java.util.Stack;

public class Visa {

    static int dist(TreeNode root, TreeNode n1, TreeNode n2) {
        TreeNode ancestor = lca(root, n1, n2);
        return length(ancestor, n1) + length(ancestor, n2) + 1;
    }

    static TreeNode lca(TreeNode root, TreeNode n1, TreeNode n2) {
        if (root == null || root == n1 || root == n2) return root;
        TreeNode left = lca(root.left, n1, n2);
        TreeNode right = lca(root.right, n1, n2);
        if (left == null && right == null) return null;
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }

    static int length(TreeNode root, TreeNode child) {
        if (root == child) return 0;
        if (root.left != null) {
            int lenLeft = length(root.left, child);
            if (lenLeft != -1) return lenLeft + 1;
        }
        if (root.right != null) {
            int lenRight = length(root.right, child);
            if (lenRight != -1) return lenRight + 1;
        }
        return -1;
    }

    static void preorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            doStuff(cur);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
    }

    static void inorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            doStuff(cur);
            cur = cur.right;
        }
    }

    static void postorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        do {
            // push right and cur then move left
            while (cur != null) {
                if (cur.right != null) stack.push(cur.right);
                stack.push(cur);
                cur = cur.left;
            }

            if (!stack.isEmpty()) {
                cur = stack.pop();
                if (!stack.isEmpty() && cur.right == stack.peek()) {
                    // trav right child first
                    stack.pop();
                    stack.push(cur);
                    cur = cur.right;
                } else {
                    // right done
                    doStuff(cur);
                    cur = null;
                }
            }
        } while (!stack.isEmpty());
    }

    private static void doStuff(TreeNode n) {
        System.out.println(n.val);
    }

    public static void main(String[] args) {
        //         5
        //        3  10
        //      2  4
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(10);
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    TreeNode(int val) {
        this.val = val;
    }
}

