package median

import java.util.*

class Sol150 {

    fun evalRPN(tokens: Array<String>): Int {
        val stack = Stack<String>()
        for (t in tokens) {
            if (setOf("+", "-", "*", "/").contains(t)) {
                val n1 = stack.pop().toInt()
                val n2 = stack.pop().toInt()
                when (t) {
                    "+" -> stack.push((n1 + n2).toString())
                    "-" -> stack.push((n2 - n1).toString())
                    "*" -> stack.push((n1 * n2).toString())
                    "/" -> stack.push((n2 / n1).toString())
                }
            } else {
                stack.push(t)
            }
        }
        return stack.pop().toInt()
    }

}