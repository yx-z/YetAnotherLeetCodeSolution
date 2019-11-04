package median

class Sol809 {

    // linear time and space
    fun expressiveWords(S: String, words: Array<String>): Int {
        fun String.compress(): List<Pair<Char, Int>> {
            if (isEmpty()) return emptyList()
            val compressed = ArrayList<Pair<Char, Int>>()
            compressed.add(this[0] to 1)
            drop(1).forEach {
                val (c, f) = compressed.last()
                if (it == c) compressed[compressed.size - 1] = c to f + 1
                else compressed.add(it to 1)
            }
            return compressed
        }

        val sCompressed = S.compress()
        fun String.isStretchy(): Boolean {
            val wCompressed = compress()
            if (wCompressed.size != sCompressed.size) return false
            for ((w, s) in wCompressed.zip(sCompressed)) {
                val (wC, wF) = w
                val (sC, sF) = s
                if (wC != sC || (sF < 3 && wF != sF) || (sF in 3 until wF)) return false
            }
            return true
        }
        return words.count(String::isStretchy)
    }

    // linear time, constant space
    fun redo(S: String, words: Array<String>): Int {
        fun isStretchy(S: String, word: String): Boolean {
            val sL = S.length
            val wL = word.length
            var sI = 0
            var wI = 0
            fun String.repeatedLen(i: Int): Int {
                var j = i
                while (j < length && this[j] == this[i]) j++
                return j - i
            }
            while (sI < sL && wI < wL) {
                if (S[sI] != word[wI]) return false
                val sRL = S.repeatedLen(sI)
                val wRL = word.repeatedLen(wI)
                if ((sRL < 3 && sRL != wRL) || (sRL in 3 until wRL)) return false
                sI += sRL
                wI += wRL
            }
            return sI == sL && wI == wL
        }
        return words.count { isStretchy(S, it) }
    }
}