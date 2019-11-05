package google;

import java.util.*;

public class Sol133 {

    Map<Node, Node> copy = new HashMap<>();
    Set<Node> visited = new HashSet<>();

    public Node cloneGraph(Node node) {
        copy(node);
        conn(node);
        return copy.get(node);
    }

    private void copy(Node node) {
        if (!copy.containsKey(node)) {
            copy.put(node, new Node(node.val, new ArrayList<>()));
            node.neighbors.forEach(this::copy);
        }
    }

    private void conn(Node node) {
        if (!visited.contains(node)) {
            visited.add(node);
            Node c = copy.get(node);
            for (Node n : node.neighbors) {
                c.neighbors.add(copy.get(n));
                if (!visited.contains(n)) conn(n);
            }
        }
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
