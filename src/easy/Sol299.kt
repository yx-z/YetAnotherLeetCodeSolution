package easy

import kotlin.math.min

class Sol299 {

    fun getHint(secret: String, guess: String): String {
        val freqS = HashMap<Char, Int>()
        val freqG = HashMap<Char, Int>()
        var a = 0
        var b = 0
        for ((si, gi) in secret.zip(guess)) {
            if (si == gi) a++
            else {
                freqS[si] = (freqS[si] ?: 0) + 1
                freqG[gi] = (freqG[gi] ?: 0) + 1
            }
        }
        freqG.filter { it.key in freqS }.forEach { (k, v) ->
            b += min(v, freqS[k]!!)
        }
        return "${a}A${b}B"
    }
}