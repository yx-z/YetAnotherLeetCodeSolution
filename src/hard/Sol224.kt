package hard

import java.util.*

class Sol224 {

    fun calculate(s: String): Int {
        val s = s.replace(" ", "")
        var sgn = 1
        val res = Stack<Int>().apply { push(0) }
        var i = 0
        while (i < s.length) {
            when (s[i]) {
                '+' -> sgn = 1
                '-' -> sgn = -1
                '(' -> {
                    res.push(sgn)
                    sgn = 1
                    res.push(0)
                }
                ')' -> {
                    val num = res.pop()
                    val preSgn = res.pop()
                    res.push(res.pop() + preSgn * num)
                }
                else -> {
                    val p = i
                    while (i < s.length && s[i].isDigit()) i++
                    i--
                    val num = s.substring(p..i).toInt()
                    res.push(res.pop() + sgn * num)
                }
            }
            i++
        }
        return res.peek()
    }
}