package easy

import java.util.*
import kotlin.Int.Companion.MIN_VALUE

class Sol716 {

    class MaxStack {
        private val numStack: Stack<Int> = Stack()
        private val maxStack: Stack<Int> = Stack<Int>().apply { add(MIN_VALUE) }

        fun push(x: Int) {
            if (x >= maxStack.peek()) maxStack.push(x)
            numStack.push(x)
        }

        fun pop(): Int {
            val ret = numStack.pop()
            if (maxStack.peek() == ret) maxStack.pop()
            return ret
        }

        fun top() = numStack.peek()

        fun peekMax() = maxStack.peek()

        fun popMax(): Int {
            val ret = maxStack.pop()
            val tmp = Stack<Int>()
            while (numStack.peek() != ret) tmp.push(numStack.pop())
            numStack.pop()
            while (tmp.isNotEmpty()) push(tmp.pop())
            return ret
        }
    }
}