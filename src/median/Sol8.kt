package median

class Sol8 {

    fun myAtoi(s: String): Int {
        if (s.isBlank()) return 0
        val s = s.trimStart()
        if (!s[0].isDigit() && s[0] !in setOf('+', '-')) return 0
        var res = 0L
        var i = if (s[0].isDigit()) 0 else 1
        while (i < s.length && s[i].isDigit()) {
            res *= 10
            if (s[0] == '-') res -= s[i] - '0' else res += s[i] - '0'
            if (res < Int.MIN_VALUE) return Int.MIN_VALUE
            if (res > Int.MAX_VALUE) return Int.MAX_VALUE
            i++
        }
        return res.toInt()
    }
}