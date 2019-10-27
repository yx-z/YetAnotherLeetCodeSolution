package google;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinStep {

    // O(n) time, O(n) space
    @NotNull
    public static Set<List<Integer>> minStep(@NotNull int[] steps) {
        // preprocess
        int n = steps.length;
        List<StepNode> nodes = IntStream.range(0, n)
                .mapToObj(StepNode::new).collect(Collectors.toList());
        Map<Integer, Set<StepNode>> group = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int value = steps[i];
            if (!group.containsKey(value)) {
                group.put(value, new HashSet<>());
            }
            group.get(value).add(nodes.get(i));
        }
        // bfs
        Queue<StepNode> queue = new ArrayDeque<>();
        int step = 0;
        queue.add(nodes.get(0));
        nodes.get(0).minStep = step;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                StepNode curNode = queue.remove();
                int idx = curNode.value;
                if (idx < n - 1) {
                    // pre
                    if (idx > 0) {
                        StepNode pre = nodes.get(idx - 1);
                        if (step <= pre.minStep) {
                            pre.minStep = step;
                            pre.parents.add(curNode);
                            queue.add(pre);
                        }
                    }
                    // nex
                    StepNode nex = nodes.get(idx + 1);
                    if (step <= nex.minStep) {
                        nex.minStep = step;
                        nex.parents.add(curNode);
                        queue.add(nex);
                    }
                    // jump
                    int value = steps[idx];
                    final int curStep = step;
                    group.get(value).stream().filter(node ->
                            node != curNode && curStep <= node.minStep)
                            .forEach(node -> {
                                node.minStep = curStep;
                                node.parents.add(curNode);
                                queue.add(node);
                            });
                }
            }
        }
        return rootPath(nodes.get(n - 1));
    }

    // root to leaf path
    @NotNull
    private static Set<List<Integer>> rootPath(@NotNull StepNode leaf) {
        Set<List<Integer>> res = new HashSet<>();
        if (leaf.parents.isEmpty()) {
            List<Integer> single = new ArrayList<>();
            single.add(leaf.value);
            res.add(single);
            return res;
        }
        leaf.parents.stream().flatMap(p -> rootPath(p).stream()).forEach(ls -> {
            ls.add(leaf.value);
            res.add(ls);
        });
        return res;
    }
}

class StepNode {
    int value;
    int minStep = Integer.MAX_VALUE;
    @NotNull
    Set<StepNode> parents = new HashSet<>();

    public StepNode(int value) {
        this.value = value;
    }
}

