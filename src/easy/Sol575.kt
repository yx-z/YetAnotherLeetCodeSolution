package easy

import kotlin.math.min

class Sol575 {
    fun distributeCandies(candies: IntArray) =
        min(candies.size / 2, candies.toSet().size)
}