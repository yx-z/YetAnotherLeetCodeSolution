package median

class Sol139 {

    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val l = s.length
        val dp = Array(l) { false }
        dp[l - 1] = wordDict.contains(s[l - 1].toString())
        for (i in l - 2 downTo 0) {
            dp[i] = (i until l - 1).any {
                dp[it + 1] && wordDict.contains(s.substring(i..it))
            } || wordDict.contains(s.substring(i until l))
        }
        return dp[0]
    }
}