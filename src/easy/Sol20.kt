package easy

import java.util.*

class Sol20 {
    private val pairs = mapOf(')' to '(', '}' to '{', ']' to '[')

    fun isValid(s: String): Boolean {
        val st = Stack<Char>()
        for (c in s) {
            if (pairs.values.contains(c)) {
                st.push(c)
            } else {
                if (st.isEmpty() || st.pop() != pairs[c]) {
                    return false
                }
            }
        }
        return st.isEmpty()
    }
}
