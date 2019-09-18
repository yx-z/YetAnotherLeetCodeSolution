package median

import java.util.*

class Sol901 {
    class StockSpanner {
        val stack = Stack<Pair<Int, Int>>()

        fun next(price: Int): Int {
            var res = 1
            while (stack.isNotEmpty() && stack.peek().first <= price)
                res += stack.pop().second
            stack.push(price to res)
            return res
        }
    }
}