package google;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class FormTree {

    static Node form(@NotNull int[][] edges) throws IllegalArgumentException {
        Map<Integer, Node> nodes = new HashMap<>();
        Map<Node, Node> parent = new HashMap<>();
        for (int[] edge : edges) {
            Node from = nodes.computeIfAbsent(edge[0], Node::new);
            Node to = nodes.computeIfAbsent(edge[1], Node::new);
            from.children.add(to);
            if (parent.containsKey(to)) {
                throw new IllegalArgumentException("Double parent.");
            }
            parent.put(to, from);
        }
        Set<Node> rootCandidates = nodes.values().stream()
                .filter(n -> !parent.containsKey(n))
                .collect(Collectors.toSet());
        if (rootCandidates.size() == 1) {
            return rootCandidates.iterator().next();
        }
        if (rootCandidates.size() == 0) {
            throw new IllegalArgumentException("No root.");
        }
        // rootCandidates.size() > 1
        throw new IllegalArgumentException("Multiple roots.");
    }


    public static void main(String[] args) {
        System.out.println(form(new int[][]{
                {1, 2}, {1, 3}, {1, 4}, {3, 5}, {4, 6}, {4, 7}
        }));
    }
}

class Node {
    int value;
    List<Node> children = new ArrayList<>();

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.remove();
                if (i == 0) {
                    res.append(',');
                }
                res.append(cur.value);
                queue.addAll(cur.children);
            }
            res.append('\n');
        }
        return res.toString();
    }
}
