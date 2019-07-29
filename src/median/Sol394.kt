package median

import java.util.*

class Sol394 {

    fun decodeString(s: String): String {
        val res = Stack<StringBuilder>().apply { add(StringBuilder()) }
        var i = 0
        while (i < s.length) {
            when {
                s[i].isDigit() -> {
                    val p = i
                    while (s[i].isDigit()) i++
                    val count = s.substring(p, i)
                    res.push(StringBuilder(count))
                    res.push(StringBuilder())
                }
                s[i] == ']' -> {
                    val str = res.pop()
                    val rep = res.pop().toString().toInt()
                    res.peek().append(str.repeat(rep))
                }
                else -> res.peek().append(s[i])
            }
            i++
        }
        return res.peek().toString()
    }
}