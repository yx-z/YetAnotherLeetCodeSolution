package easy

class Sol392 {

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