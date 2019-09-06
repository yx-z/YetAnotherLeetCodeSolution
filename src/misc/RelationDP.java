package misc;

import java.util.*;
import java.util.stream.Collectors;

public class RelationDP {
    // <name1, <name2, [relations from name1 to name2]>>
    Map<String, Map<String, List<String>>> graph = new HashMap<>();
    Set<String> seen = new HashSet<>();
    String target = null;

    public static void main(String[] args) {
        System.out.println(new RelationDP().path(Arrays.asList(
                Arrays.asList("B", "brother", "L"),
                Arrays.asList("B", "son", "H"),
                Arrays.asList("M", "wife", "H"),
                Arrays.asList("L", "daughter", "H")
        ), "B", "H"));
    }

    public List<String> path(List<List<String>> relations,
                             String person1,
                             String person2) {
        target = person2;
        for (List<String> relation : relations) {
            String name1 = relation.get(0);
            String relate = relation.get(1);
            String name2 = relation.get(2);
            if (!graph.containsKey(name1)) {
                graph.put(name1, new HashMap<>());
            }
            if (!graph.get(name1).containsKey(name2)) {
                graph.get(name1).put(name2, new ArrayList<>());
            }
            graph.get(name1).get(name2).add(name1 + " " + relate + " " + name2);
        }
        if (!graph.containsKey(person1)) {
            return Collections.emptyList();
        }

        dfs(person1);
        return graph.get(person1)
                .getOrDefault(person2, Collections.emptyList());
    }

    private void dfs(String p1) {
        if (seen.contains(p1) || !graph.containsKey(p1)) {
            return;
        }

        seen.add(p1);
        Map<String, List<String>> p1Neighbors = graph.get(p1);
        p1Neighbors.keySet().forEach(this::dfs);

        List<String> p1Target = new ArrayList<>();
        p1Neighbors.forEach((p2, p1p2s) -> {
            if (graph.containsKey(p2)) {
                Map<String, List<String>> p2Neighbors = graph.get(p2);
                if (p2Neighbors.containsKey(target)) {
                    p2Neighbors.get(target).forEach(p2p3 -> {
                        String concat = p2p3.substring(p2p3.indexOf(' '));
                        p1Target.addAll(p1p2s.stream()
                                .map(p1p2 -> p1p2 + concat)
                                .collect(Collectors.toList()));
                    });
                }
            }
        });

        if (p1Neighbors.containsKey(target)) {
            p1Neighbors.get(target).addAll(p1Target);
        } else {
            p1Neighbors.put(target, p1Target);
        }
    }
}
