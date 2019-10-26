package hard

import java.util.*
import kotlin.math.max

class Sol84 {

    fun largestRectangleArea(bars: IntArray): Int {
        val len = bars.size
        val stack = Stack<Int>()
        var maxArea = 0
        var idx = 0
        while (idx <= len) {
            val currH = if (idx == len) 0 else bars[idx]
            if (stack.isEmpty() || currH >= bars[stack.peek()]) {
                stack.push(idx)
                idx++
            } else { // stack.isNotEmpty() && currH < bars[stack.peek()]
                maxArea = max(
                    maxArea, bars[stack.pop()] * //  calcHeight
                            // stack is empty => any bar in 0 until idx is taller than calcHeight
                            if (stack.isEmpty()) idx
                            // otherwise, any bar in stack.peek() + 1 until idx is taller than calcHeight
                            // so it is safe to calculate width this way
                            else ((idx - 1) - (stack.peek() + 1) + 1)
                )
                // note that we are not updating idx in else branch
            }
        }
        return maxArea
    }

    fun redo(bars: IntArray): Int {
        val n = bars.size
        // sentinel values: bars[-1] = bars[n] = -1
        // left[i] = max j in -1 until i : bars[j] < bars[i]
        val left = IntArray(n) { -1 }
        // right[i] = min j in i + 1..n : bars[j] < bars[i]
        val right = IntArray(n) { n }
        /*
         build left and right array in O(n)
         faster than O(n^2) naively expanding around each index as center

         why? consider building left array (right array case is similar)
         we see a while loop inside a for loop, but l is not jumping 1 by 1
         it is NOT average case, even in worst case, each index is visited
         by l at most twice inside the while loop

         to reiterate, left[l] = max j in -1 until i : bars[j] < bars[l]
         in the while loop l is assigned to left[l] == j
         now suppose bars[j] < bars[i], we stop immediately
         otherwise bars[j] >= bars[i], we will continue in the while loop

         however next time when we build for i', assuming i' > i,
         since bars[i] <= bars[j], it is either the case that left[i'] = i,
         i.e. we break while loop at i and not even touching j, or l will skip j
         and directly go to left[i] < j, skipping j

         to be more concise, as bars[j] >= bars[i], j is shadowed by i
        */
        for (i in 1 until n) {
            var l = i - 1
            while (l >= 0 && bars[l] >= bars[i]) l = left[l]
            left[i] = l
        }
        for (i in n - 2 downTo 0) {
            var r = i + 1
            while (r < n && bars[r] >= bars[i]) r = right[r]
            right[i] = r
        }

        // get max area as in naive algorithm
        var maxArea = 0
        bars.indices.forEach {
            maxArea = max(
                maxArea,
                // height * width
                bars[it] * ((right[it] - 1) - (left[it] + 1) + 1)
            )
        }
        return maxArea
    }
}