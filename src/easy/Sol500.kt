package easy

import java.util.*

class Sol500 {

    fun findWords(words: Array<String>): Array<String> {
        val ROW_1 = "qwertyuiop"
        val ROW_2 = "asdfghjkl"
        val ROW_3 = "zxcvbnm"
        val map = HashMap<Char, Int>()
        for (c in ROW_1) map[c] = 1
        for (c in ROW_2) map[c] = 2
        for (c in ROW_3) map[c] = 3
        val result = ArrayList<String>()
        outer@ for (word in words) {
            val id = map[Character.toLowerCase(word[0])]
            for (c in word) if (map[c.toLowerCase()] != id) continue@outer
            result.add(word)
        }
        return result.toTypedArray()
    }
}