package easy

import java.util.*

class Sol155 {
    /**
     * Your MinStack object will be instantiated and called as such:
     * var obj = MinStack()
     * obj.push(x)
     * obj.pop()
     * var param_3 = obj.top()
     * var param_4 = obj.getMin()
     */
    class MinStack {
        /** initialize your data structure here. */
        private val stack = Stack<Int>()

        init {
            stack.push(Integer.MAX_VALUE)
        }

        fun push(x: Int) {
            val tmpMin = stack.peek()
            stack.push(x)
            if (x >= tmpMin) {
                stack.push(tmpMin)
            } else {
                stack.push(x)
            }
        }

        fun pop() {
            stack.pop()
            stack.pop()
        }

        fun top(): Int {
            val tmp = stack.pop()
            val topVal = stack.peek()
            stack.push(tmp)
            return topVal
        }

        fun getMin() = stack.peek()!!
    }
}