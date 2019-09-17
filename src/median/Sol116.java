package median;

public class Sol116 {

    public Node connect(Node root) {
        if (root == null) return null;
        conn(root.left, root.right);
        return root;
    }

    public void conn(Node left, Node right) {
        if (left == null || right == null) return;
        conn(left.left, left.right);
        conn(right.left, right.right);
        conn(left.right, right.left);
        left.next = right;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
