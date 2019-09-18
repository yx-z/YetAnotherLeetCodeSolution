package median

class Sol435 {

    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        if (intervals.isEmpty()) return 0
        intervals.sortBy { it[1] }
        val n = intervals.size
        var nonoverlap = 1
        var last = intervals[0][1]
        for (int in intervals.drop(1)) {
            if (int[0] >= last) {
                last = int[1]
                nonoverlap++
            }
        }
        return n - nonoverlap
    }
}