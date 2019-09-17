package median;

public class Sol117 {
    // recursive O(n) space
    public Node connect(Node root) {
        if (root == null) return null;
        if (root.left != null && root.right != null)
            root.left.next = root.right;
        else if (root.left != null) root.left.next = next(root.next);
        if (root.right != null) root.right.next = next(root.next);
        connect(root.right);
        connect(root.left);
        return root;
    }

    public Node next(Node root) {
        if (root == null) return null;
        while (root != null && root.left == null && root.right == null)
            root = root.next;
        if (root == null) return null;
        if (root.left == null) return root.right;
        return root.left;
    }

    // iterative O(1) space
    public Node conn(Node root) {
        Node cur = root;
        while (cur != null) {
            Node head = new Node(0, null, null, null);
            Node tail = head;
            while (cur != null) {
                if (cur.left != null) {
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }
            cur = head.next;
        }

        return root;
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
