package median

import java.util.*

class Sol227 {

    fun calculate(s: String): Int {
        val s = "+" + s.replace(" ", "")
        val r = Stack<Int>()
        val par = { i: Int ->
            var p = i
            while (p < s.length && s[p].isDigit()) p++
            p - 1 to s.substring(i, p).toInt()
        }
        var i = 0
        while (i < s.length) {
            val c = s[i]
            when (c) {
                '+', '-' -> {
                    val (idx, num) = par(i + 1)
                    i = idx
                    r.push(if (c == '+') num else -num)
                }
                '*', '/' -> {
                    val (idx, num) = par(i + 1)
                    i = idx
                    r.push(if (c == '*') r.pop() * num else r.pop() / num)
                }
            }
            i++
        }
        while (r.size > 1) {
            val n1 = r.pop()
            val n2 = r.pop()
            r.push(n1 + n2)
        }
        return r.peek()
    }
}