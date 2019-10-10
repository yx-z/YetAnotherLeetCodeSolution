package median

class Sol792 {

    fun numMatchingSubseq(S: String, words: Array<String>) =
        words.count { isSubsequence(it, S) }

    // Sol392
    fun isSubsequence(s: String, t: String): Boolean {
        var si = 0
        var ti = 0
        while (ti < t.length) {
            if (s[si] == t[ti]) {
                si++
                if (si == s.length) return true
            }
            ti++
        }
        return false
    }
}