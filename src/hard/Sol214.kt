package hard

class Sol214 {

    fun shortestPalindrome(s: String): String {
        val MOD = 1_000_000_007L
        val BASE = 29L

        var pow = 1L
        var hash1 = 0L
        var hash2 = 0L
        var pos = 0
        for (i in s.indices) {
            hash1 = (hash1 * BASE + s[i].toInt()) % MOD
            hash2 = (hash2 + s[i].toInt() * pow) % MOD
            if (hash1 == hash2) {
                pos = i + 1
            }
            pow = (pow * BASE) % MOD
        }
        return s.substring(pos).reversed() + s
    }
}