package google;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnumPath {

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
}
