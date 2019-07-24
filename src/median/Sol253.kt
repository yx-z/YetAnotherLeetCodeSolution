package median

class Sol253 {
    class Interval(var start: Int = 0, var end: Int = 0)

    fun minMeetingRooms(intervals: Array<Interval>): Int {
        if (intervals.isEmpty()) return 0
        val arr = IntArray(intervals.map { it.end }.max()!! + 1)
        intervals.forEach { for (i in it.start..it.end) arr[i]++ }
        return arr.max()!!
    }
}

