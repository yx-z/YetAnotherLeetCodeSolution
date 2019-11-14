package google;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class UniqueLog {

    static int uniqueLogs(@NotNull String[][] logs) {
        var count = 0;
        var ids = new HashSet<String>();
        for (var log : logs) {
            var id1 = log[0];
            var id2 = log[1];
            if (!(ids.contains(id1) || ids.contains(id2))) count++;
            ids.add(id1);
            ids.add(id2);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(uniqueLogs(new String[][]{
                {"a", "d"}, {"a", "b"}, {"b", "c"}, {"g", "h"}, {"g", "k"}
        }));
    }
}
