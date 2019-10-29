package median

class Sol151 {

    fun reverseWords(s: String) = s.trim().split(' ')
        .filter { it.isNotEmpty() }.reversed().joinToString(" ")

    fun redo(s: String): String {
        val n = s.length
        val ca = s.toCharArray()
        var start = 0
        while (start < n && ca[start] == ' ') {
            start++
        }
        if (start == n) {
            return ""
        }
        var end = n - 1
        while (ca[end] == ' ') {
            end--
        }

        // ca[start..end] has no leading or trailing spaces
        // reverse whole string
        ca.reverse(start, end)
        // reverse each word, based on Sol557
        var i = start
        while (i <= end) {
            if (ca[i] != ' ') {
                var j = i
                while (j <= end && ca[j] != ' ') {
                    j++
                }
                j--
                ca.reverse(i, j)
                i = j
            }
            i++
        }

        // collect
        val sb = StringBuilder()
        for (j in start..end) {
            // at most one space between words
            if (!(ca[j] == ' ' && ca[j - 1] == ' ')) {
                sb.append(ca[j])
            }
        }
        return sb.toString()
    }

    fun CharArray.reverse(l: Int = 0, h: Int = size - 1) {
        var l = l
        var h = h
        while (l < h) {
            swap(l, h)
            l++
            h--
        }
    }

    fun CharArray.swap(l: Int, h: Int) {
        val tmp = this[l]
        this[l] = this[h]
        this[h] = tmp
    }
}