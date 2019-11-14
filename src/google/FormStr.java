package google;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FormStr {

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
                if (curr == null) return -1;
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
                if (!root.children.containsKey(c))
                    root.children.put(c, new SuffixNode(c));
                curr = root.children.get(c);
            }
            curr.isEnd = true;
        }
        return root;
    }

    public int shortestWay(@NotNull String source, @NotNull String target) {
        int m = source.length();
        // dp[i][j] = min idx in source[i until m] : source[idx] == j
        Map[] dp = new HashMap[m];
        dp[m - 1] = new HashMap<Character, Integer>();
        dp[m - 1].put(source.charAt(m - 1), m - 1);
        for (int i = m - 2; i >= 0; i--) {
            dp[i] = new HashMap<Character, Integer>(dp[i + 1]);
            dp[i].put(source.charAt(i), i);
        }
        int count = 0;
        int idx = 0;
        for (char c : target.toCharArray()) {
            if (!dp[0].containsKey(c)) return -1;
            if (!dp[idx].containsKey(c)) {
                count++;
                idx = 0;
            }
            idx = (int) dp[idx].get(c) + 1;
            if (idx == m) {
                count++;
                idx = 0;
            }
        }
        return count + (idx > 0 ? 1 : 0);
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

