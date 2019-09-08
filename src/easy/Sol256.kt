package easy

import kotlin.math.min

class Sol256 {

    fun minCostToPaint(costs: List<IntArray>): Int {
        val n = costs.size
        // red/blue/green[i] = min cost of painting house [i until n]
        //                     given that house[i] is pained red/blue/green
        val red = IntArray(n).apply { this[n - 1] = costs[n - 1][0] }
        val blue = IntArray(n).apply { this[n - 1] = costs[n - 1][1] }
        val green = IntArray(n).apply { this[n - 1] = costs[n - 1][2] }
        for (i in n - 2 downTo 0) {
            red[i] = costs[i][0] + min(blue[i + 1], green[i + 1])
            blue[i] = costs[i][1] + min(red[i + 1], green[i + 1])
            green[i] = costs[i][2] + min(red[i + 1], blue[i + 1])
        }
        return min(min(red[0], blue[0]), green[0])
    }
}