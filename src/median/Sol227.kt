package median

import java.util.*

class Sol227 {

    fun calculate(s: String): Int {
        val s = "+" + s.replace(" ", "")
        val par = { i: Int ->
            var p = i
            while (p < s.length && s[p].isDigit()) p++
            p to s.substring(i, p).toInt()
        }
        val r = Stack<Int>()
        var i = 0
        while (i < s.length) {
            val (idx, num) = par(i + 1)
            when (s[i]) {
                '+' -> r.push(num)
                '-' -> r.push(-num)
                '*' -> r.push(r.pop() * num)
                '/' -> r.push(r.pop() / num)
            }
            i = idx
        }
        while (r.size > 1) {
            val n1 = r.pop()
            val n2 = r.pop()
            r.push(n1 + n2)
        }
        return r.peek()
    }
}