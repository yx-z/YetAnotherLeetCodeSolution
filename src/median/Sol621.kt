package median

import java.lang.Math.max

class Sol621 {

    fun leastInterval(tasks: CharArray, n: Int): Int {
        val freq = tasks.toList().groupingBy { it }.eachCount()
        val maxFreq = freq.values.max() ?: 0
        val maxCount = freq.values.count { it == maxFreq }
        val partCount = maxFreq - 1
        val partLen = n - (maxCount - 1)
        val emptySlots = partCount * partLen
        val available = tasks.size - maxFreq * maxCount
        val idles = max(0, emptySlots - available)
        return tasks.size + idles
    }
}