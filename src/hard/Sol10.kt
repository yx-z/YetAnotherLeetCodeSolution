package hard

class Sol10 {

	// O(mn) dp
	fun isMatch(s: String, p: String): Boolean {
		val m = s.length
		val n = p.length
		val v = Array(m + 1) { BooleanArray(n + 1) }.apply { this[m][n] = true }
		for (i in m downTo 0) {
			for (j in n - 1 downTo 0) {
				if (p[j] != '*') {
					val curr = i < m && (p[j] == s[i] || p[j] == '.')
					v[i][j] = if (j + 1 < n && p[j + 1] == '*')
						v[i][j + 2] || (curr && v[i + 1][j])
					else curr && v[i + 1][j + 1]
				}
			}
		}
		return v[0][0]
	}
}
