import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Google {

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

    public static int formStrSubseq(@NotNull String source,
                                    @NotNull String target) {
        int m = source.length();
        int n = target.length();
        int j = 0;
        int count = 0;
        while (j < n) {
            int i = 0;
            int curJ = j;
            while (i < m) {
                if (source.charAt(i) == target.charAt(curJ)) {
                    curJ++;
                }
                if (curJ == n) {
                    break;
                }
                i++;
            }
            if (j == curJ) {
                return -1;
            }
            j = curJ + 1;
            count++;
        }
        return count;
    }

    public static int formStrSubstr(@NotNull String source,
                                    @NotNull String target) {
        SuffixNode root = build(source);
        int count = 0;
        SuffixNode curr = root;
        for (char c : target.toCharArray()) {
            curr = curr.children.get(c);
            if (curr == null) {
                curr = root.children.get(c);
                if (curr == null) {
                    return -1;
                }
                count++;
            }
        }
        return count;
    }

    private static SuffixNode build(@NotNull String source) {
        int n = source.length();
        SuffixNode root = new SuffixNode('\0');
        for (int i = 0; i < n; i++) {
            SuffixNode curr = root;
            for (int j = i; j < n; j++) {
                char c = source.charAt(j);
                if (!root.children.containsKey(c)) {
                    root.children.put(c, new SuffixNode(c));
                }
                curr = root.children.get(c);
            }
            curr.isEnd = true;
        }
        return root;
    }

    @NotNull
    public static Set<List<Integer>> enumPath(int i1, int i2) {
        List<Integer> start = new ArrayList<>();
        start.add(i1);
        start.add(i2);
        Set<Integer> diff = new HashSet<>();
        diff.add(i1);
        diff.add(i2);
        return enumPath(start, diff);
    }

    @NotNull
    private static Set<List<Integer>> enumPath(@NotNull List<Integer> start,
                                               @NotNull Set<Integer> diffs) {
        Set<List<Integer>> res = new HashSet<>();
        int n = start.size();
        boolean extended = false;
        int last = start.get(n - 1);
        for (int i = 0; i < n - 1; i++) {
            int diff = Math.abs(start.get(i) - last);
            if (!diffs.contains(diff)) {
                extended = true;
                diffs.add(diff);
                List<Integer> lsCopy = new ArrayList<>(start);
                lsCopy.add(diff);
                Set<Integer> setCopy = new HashSet<>(diffs);
                setCopy.add(diff);
                res.addAll(enumPath(lsCopy, setCopy));
            }
        }
        if (!extended) {
            res.add(start);
        }
        return res;
    }

    public static void main(String[] args) {
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

class SuffixNode {
    char value;
    boolean isEnd = false;
    @NotNull
    Map<Character, SuffixNode> children = new HashMap<>();

    public SuffixNode(char value) {
        this.value = value;
    }
}

class HistoricalMap<K, V> {
    Map<K, TreeMap<Long, V>> data = new HashMap<>();

    void put(@NotNull K key, @NotNull V value) {
        long time = System.currentTimeMillis();
        if (!data.containsKey(key)) {
            data.put(key, new TreeMap<>());
        }
        data.get(key).put(time, value);
    }

    @Nullable
    V get(@NotNull K key, long time) {
        if (!data.containsKey(key)) {
            return null;
        }
        Map.Entry<Long, V> value = data.get(key).floorEntry(time);
        if (value == null) {
            return null;
        }
        return value.getValue();
    }
}