package hard

import java.util.*
import kotlin.math.max

class Sol84 {

    fun largestRectangleArea(heights: IntArray): Int {
        var maxArea = 0
        val stack = Stack<Int>()
        var p = 0
        while (p < heights.size) {
            if (stack.isEmpty()) {
                stack.push(p)
                p++
            } else {
                val top = stack.peek()
                if (heights[p] >= heights[top]) {
                    stack.push(p)
                    p++
                } else {
                    val height = heights[stack.pop()]
                    val leftLessMin = if (stack.isEmpty()) -1 else stack.peek()
                    val rightLessMin = p
                    val area = (rightLessMin - leftLessMin - 1) * height
                    maxArea = max(area, maxArea)
                }
            }
        }
        while (stack.isNotEmpty()) {
            val height = heights[stack.pop()]
            val leftLessMin = if (stack.isEmpty()) -1 else stack.peek()
            val rightLessMin = heights.size
            val area = (rightLessMin - leftLessMin - 1) * height
            maxArea = max(area, maxArea)
        }
        return maxArea
    }
}