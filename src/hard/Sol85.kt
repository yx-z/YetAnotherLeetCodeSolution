package hard

import java.util.*
import kotlin.math.max

class Sol85 {

    fun maximalRectangle(mat: Array<CharArray>): Int {
        val m = mat.size
        if (m == 0) return 0
        val n = mat[0].size
        if (n == 0) return 0
        val height = IntArray(n)
        val rect = {
            var maxArea = 0
            val stack = Stack<Int>()
            var p = 0
            while (p < n) {
                if (stack.isEmpty()) {
                    stack.push(p)
                    p++
                } else {
                    val top = stack.peek()
                    if (height[p] >= height[top]) {
                        stack.push(p)
                        p++
                    } else {
                        val h = height[stack.pop()]
                        val left = if (stack.isEmpty()) -1 else stack.peek()
                        val right = p
                        val area = (right - left - 1) * h
                        maxArea = max(maxArea, area)
                    }
                }
            }
            while (stack.isNotEmpty()) {
                val h = height[stack.pop()]
                val left = if (stack.isEmpty()) -1 else stack.peek()
                val right = p
                val area = (right - left - 1) * h
                maxArea = max(maxArea, area)
            }
            maxArea
        }
        var maxArea = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                height[j] = if (mat[i][j] == '1') height[j] + 1 else 0
            }
            maxArea = max(maxArea, rect())
        }
        return maxArea
    }
}