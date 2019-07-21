package hard

import java.util.*

class Sol301 {
    val ans = HashSet<String>()

    fun removeInvalidParentheses(s: String): List<String> {
        for (i in 0..s.length) {
            s.toCharArray().remove(i)
            if (ans.isNotEmpty()) break
        }
        return ans.toList()
    }

    fun CharArray.remove(count: Int, start: Int = 0) {
        when {
            count == 0 && isValid() -> ans.add(
                filterNot { it == '_' }.joinToString("")
            )
            start + count <= size -> {
                copyOf().apply { this[start] = '_' }
                    .remove(count - 1, start + 1)
                remove(count, start + 1)
            }
        }
    }

    fun CharArray.isValid(): Boolean {
        val stack = Stack<Char>()
        forEach {
            when (it) {
                '(' -> stack.push('(')
                ')' -> if (stack.isEmpty()) return false else stack.pop()
            }
        }
        return stack.isEmpty()
    }
}