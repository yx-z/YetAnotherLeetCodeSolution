package google;

import java.util.*;

public class ContainsCycle {

    static Set<Integer> visited = new HashSet<>();
    static Map<Integer, List<Integer>> graph = null;

    static boolean containsCycle(Map<Integer, List<Integer>> map) {
        graph = map;
        return map.keySet().stream().anyMatch(n -> hasCycle(n, -1));
    }

    static boolean hasCycle(int start, int parent) {
        if (visited.contains(start)) return false;
        visited.add(start);
        for (var n : graph.get(start))
            if (visited.contains(n)) {
                if (parent != n) return true;
            } else if (hasCycle(n, start)) return true;
        return false;
    }

    public static void main(String[] args) {
        var g = new HashMap<Integer, List<Integer>>();
        g.put(0, Collections.singletonList(1));
        g.put(1, Collections.singletonList(0));
        g.put(2, Collections.emptyList());
        System.out.println(containsCycle(g));
    }
}
