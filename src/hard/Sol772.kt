package hard

import java.util.*

class Sol772 {
    fun calculate(s: String, i: Int = 0): Pair<Int, Int> {
        val r = Stack<Int>()
        var op = '+'
        var n: Int
        var i = i
        while (i < s.length && op != ')') {
            if (s[i] == '(') {
                calculate(s, i + 1).run {
                    i = first
                    n = second
                }
            } else {
                parse(s, i).run {
                    i = first
                    n = second
                }
            }
            when (op) {
                '+' -> r.push(n)
                '-' -> r.push(-n)
                '*' -> r.push(r.pop() * n)
                '/' -> r.push(r.pop() / n)
            }
            if (i >= s.length) break
            op = s[i]
            i++
        }
        return i to r.reduce { i1, i2 -> i1 + i2 }
    }

    fun parse(s: String, i: Int): Pair<Int, Int> {
        var n = 0
        var p = i
        while (p < s.length && s[p].isDigit()) {
            n *= 10
            n += s[p] - '0'
            p++
        }
        return p to n
    }
}
