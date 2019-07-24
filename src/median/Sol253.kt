package median

class Sol253 {
    class Interval(var start: Int = 0, var end: Int = 0)

    fun minMeetingRooms(ints: Array<Interval>): Int {
        if (ints.isEmpty()) return 0
        val arr = IntArray(ints.map { it.end }.max()!! + 1)
        ints.forEach { (it.start..it.end).forEach { arr[it]++ } }
        return arr.max()!!
    }
}

