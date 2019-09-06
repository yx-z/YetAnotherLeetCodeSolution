package misc;

import java.util.*;
import java.util.stream.Collectors;

public class RelationRec {
    private String target;
    // <person1, <person2, relation>>
    private Map<String, Map<String, String>> graph = new HashMap<>();
    // <person1, [paths to target]>
    private Map<String, List<String>> results = new HashMap<>();

    public List<String> path(List<List<String>> relations,
                             String person1,
                             String person2) {
        for (List<String> relation : relations) {
            String name1 = relation.get(0);
            String relate = relation.get(1);
            String name2 = relation.get(2);

            if (!graph.containsKey(name1)) {
                graph.put(name1, new HashMap<>());
            }
            graph.get(name1).put(name2, name1 + " " + relate + " " + name2);
        }
        target = person2;
        return dfsRec(person1);
    }

    private List<String> dfsRec(String p1) {
        if (!graph.containsKey(p1)) {
            return Collections.emptyList();
        }
        if (results.containsKey(p1)) {
            return results.get(p1);
        }
        Map<String, String> neighbors = graph.get(p1);
        List<String> res = new ArrayList<>();
        for (String n : neighbors.keySet()) {
            if (n.equals(target)) {
                res.add(neighbors.get(n));
            }
            res.addAll(dfsRec(n).stream().filter(p -> p.endsWith(target))
                    .map(p -> neighbors.get(n) + p.substring(p.indexOf(' ')))
                    .collect(Collectors.toList()));
        }
        results.put(p1, res);
        return res;
    }
}
