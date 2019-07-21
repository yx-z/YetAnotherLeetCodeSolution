package easy;

import java.util.*;

public class Sol429 {

    Map<Node, Integer> nodeLevel = new HashMap<>();

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        nodeLevel.put(root, 1);
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            int level = nodeLevel.get(curr);
            nodeLevel.remove(curr);
            if (level > res.size()) res.add(new ArrayList<>());
            res.get(res.size() - 1).add(curr.val);
            curr.children.forEach(c -> {
                nodeLevel.put(c, level + 1);
                queue.add(c);
            });
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
