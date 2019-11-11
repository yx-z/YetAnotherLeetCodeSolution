package google;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.stream.Collectors;

public class TransformStr {

    static boolean canTransform(@NotNull String s1, @NotNull String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        var s2Chars = s2.chars().boxed().collect(Collectors.toSet());
        if (s2Chars.size() == 26) {
            return false;
        }
        var map = new HashMap<Character, Character>();
        for (var i = 0; i < s1.length(); i++) {
            var c1 = s1.charAt(i);
            var c2 = s2.charAt(i);
            if (!map.containsKey(c1)) {
                map.put(c1, c2);
            } else {
                if (map.get(c1) != c2) {
                    return false;
                }
            }
        }
        return map.keySet().size() >= s2Chars.size();
    }
}
