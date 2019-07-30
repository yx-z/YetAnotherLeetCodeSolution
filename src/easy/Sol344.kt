package easy

class Sol344 {

    fun reverseString(s: CharArray) {
        val n = s.size
        var l = 0
        var h = n - 1
        while (l < h) {
            val tmp = s[l]
            s[l] = s[h]
            s[h] = tmp
            l++
            h--
        }
    }
}