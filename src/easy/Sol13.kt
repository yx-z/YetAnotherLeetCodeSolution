package easy

class Sol13 {

    fun romanToInt(s: String): Int {
        val l = s.length
        val m = mapOf(
            'I' to 1, 'V' to 5, 'X' to 10, 'L' to 50,
            'C' to 100, 'D' to 500, 'M' to 1000
        )
        val n = mapOf(
            'I' to setOf('V', 'X'),
            'X' to setOf('L', 'C'),
            'C' to setOf('D', 'M')
        )
        var v = 0
        for (i in 0..l - 2) {
            var c = m.getValue(s[i])
            if (s[i + 1] in n[s[i]] ?: emptySet()) c = -c
            v += c
        }
        v += m.getValue(s[l - 1])
        return v
    }
}