package median

import java.util.*

class Sol503 {

    fun nextGreaterElements(nums: IntArray): IntArray {
        val l = nums.size
        val s = Stack<Int>()
        val res = IntArray(l) { -1 }
        for (it in 0 until 2 * l) {
            val i = if (it < l) it else it - l
            val n = nums[i]
            while (s.isNotEmpty() && nums[s.peek()] < n) {
                res[s.pop()] = n
            }
            s.push(i)
        }
        return res
    }
}