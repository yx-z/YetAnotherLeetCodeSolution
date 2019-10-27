package google;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SubstrSubseq {

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

