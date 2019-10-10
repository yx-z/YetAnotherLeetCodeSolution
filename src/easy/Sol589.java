package easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Sol589 {

    // recursive
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.add(root.val);
            root.children.forEach(n -> res.addAll(preorder(n)));
        }
        return res;
    }

    // iterative
    public List<Integer> redo(Node root) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> nodes = new Stack<>();
        if (root != null) {
            nodes.push(root);
        }
        while (!nodes.isEmpty()) {
            Node curr = nodes.pop();
            res.add(curr.val);
            for (int i = curr.children.size() - 1; i >= 0; i--) {
                nodes.add(curr.children.get(i));
            }
        }
        return res;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
