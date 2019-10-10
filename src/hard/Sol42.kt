package hard

import kotlin.math.max
import kotlin.math.min

class Sol42 {

    fun trap(height: IntArray): Int {
        val n = height.size
        if (n <= 2) return 0
        val left = IntArray(n)
        left[0] = 0
        for (i in 1 until n) {
            left[i] = if (height[i] >= height[left[i - 1]]) i else left[i - 1]
        }
        val rigt = IntArray(n)
        rigt[n - 1] = n - 1
        for (i in n - 2 downTo 0) {
            rigt[i] = if (height[i] >= height[rigt[i + 1]]) i else rigt[i + 1]
        }
        var area = 0
        for (i in 1 until n - 1) {
            if (left[i] != i && rigt[i] != i) {
                area += min(height[left[i]], height[rigt[i]]) - height[i]
            }
        }
        return area
    }

    fun redo(height: IntArray): Int {
        val n = height.size
        if (n <= 2) return 0
        var area = 0
        var left = 0
        var right = n - 1
        var lMax = 0
        var rMax = 0
        while (left <= right) {
            lMax = max(lMax, height[left])
            rMax = max(rMax, height[right])
            if (lMax <= rMax) {
                area += lMax - height[left]
                left++
            } else {
                area += rMax - height[right]
                right--
            }
        }
        return area
    }
}