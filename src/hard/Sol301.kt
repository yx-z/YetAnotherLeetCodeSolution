package hard

import java.util.*
import kotlin.collections.ArrayList

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

    val res = ArrayList<String>()
    fun redo(s: String): List<String> {
        s.dfs()
        return res
    }

    fun String.dfs(
        lastI: Int = 0, lastJ: Int = 0,
        open: Char = '(', close: Char = ')'
    ) {
        var count = 0
        var i = lastI
        while (i < length && count >= 0) {
            if (this[i] == open) count++
            if (this[i] == close) count--
            i++
        }
        if (count >= 0) {
            if (open == '(') reversed().dfs(0, 0, close, open)
            else res.add(reversed())
        } else {
            i--
            for (j in lastJ..i)
                if (this[j] == close && (j == lastJ || this[j - 1] != close))
                    (substring(0, j) + substring(j + 1)).dfs(i, j, open, close)
        }
    }
}