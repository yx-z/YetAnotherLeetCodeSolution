package citadel;

public class ConcatStr {

    public static String concat(String[] src) {
        StringBuilder sb = new StringBuilder(src[0]);
        // O(nl) : n = src.length, l = max{ src[i].length }
        for (int i = 1; i < src.length; i++) {
            sb.append(src[i].substring(rollingHash(src[i - 1], src[i])));
        }
        return sb.toString();
    }

    // return idx in 0..s2.length : s1[len(s1)-idx..len(s1)-1] == s2[0..idx-1]
    private static int rollingHash(String s1, String s2) {
        final long BASE = 29;
        final long MOD = 1000000007;

        long hash1 = 0;
        long hash2 = 0;
        long pow = 1;
        int idx = 0;
        int i = 0; // 0 until s2.length
        int j = s1.length() - 1; // s1.length - 1 downTo 0
        while (i < s2.length() && j >= 0) {
            hash1 = (hash1 + s1.charAt(j) * pow) % MOD;
            hash2 = (hash2 * BASE + s2.charAt(i)) % MOD;
            if (hash1 == hash2) {
                idx = i + 1;
            }
            pow = pow * BASE % MOD;
            i++;
            j--;
        }
        return idx;
    }
}
