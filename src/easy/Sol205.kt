package easy

class Sol205 {

    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val dic = HashMap<Char, Char>()
        for (i in s.indices) {
            val curS = s[i]
            val curT = t[i]
            if (curS in dic && dic[curS] != curT) return false
            if (curS !in dic) {
                if (curT in dic.values) return false
                dic[curS] = curT
            }
        }
        return true
    }
}