package easy

class Sol557 {

    fun reverseWords(s: String): String {
        val n = s.length
        val ca = s.toCharArray()
        var i = 0
        while (i < n) {
            if (ca[i] != ' ') {
                var j = i
                while (j < n && ca[j] != ' ') {
                    j++
                }
                j--
                ca.reverse(i, j)
                i = j
            }
            i++
        }
        return String(ca)
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