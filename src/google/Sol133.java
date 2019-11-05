package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sol133 {

    Map<Node, Node> copy = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (!copy.containsKey(node)) {
            Node c = new Node(node.val, new ArrayList<>());
            copy.put(node, c);
            node.neighbors.stream().map(this::cloneGraph)
                    .forEach(n -> c.neighbors.add(n));
        }
        return copy.get(node);
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
