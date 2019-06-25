package median

class Sol91 {

    fun numDecodings(s: String): Int {
        val l = s.length
        val dp = IntArray(l)
        if (s[0].asInt() == 0) {
            return 0
        } else {
            dp[0] = 1
        }
        for (i in 1 until l) {
            if (s[i].asInt() == 0) {
                if (s[i - 1].asInt() in 1..2) {
                    dp[i] = if (i - 2 >= 0) dp[i - 2] else 1
                } else {
                    return 0
                }
            } else {
                if (s[i - 1..i].asInt() in 11..26) {
                    dp[i] = if (i - 2 >= 0) dp[i - 1] + dp[i - 2] else 2
                } else {
                    dp[i] = dp[i - 1]
                }
            }
        }
        return dp[l - 1]
    }

    operator fun String.get(r: IntRange) = substring(r)

    private fun String.asInt() = Integer.parseInt(this)

    private fun Char.asInt() = this - '0'
}
