package hard

class Sol759 {

    class Interval(var start: Int, var end: Int) {

        override fun toString() = "[$start, $end]"
    }

    // custom signature
    fun freeTime(schedules: List<List<Interval>>): List<Interval> {
        val everyone = schedules.flatten().sortedWith(Comparator { i1, i2 ->
            if (i1.start == i2.start) i1.end.compareTo(i2.end)
            else i1.start.compareTo(i2.start)
        })
        if (everyone.isEmpty()) return emptyList()
        val merged = ArrayList<Interval>().apply { add(everyone[0]) }
        everyone.filterIndexed { i, _ -> i > 0 }.forEach {
            val l = merged.last()
            if (it.start > l.end) merged.add(it)
            else l.end = it.end
        }
        if (merged.size < 2) return emptyList()
        val res = ArrayList<Interval>().apply {
            add(Interval(merged[0].end, merged[1].start))
        }
        (2 until merged.size).forEach {
            res.add(Interval(merged[it - 1].end, merged[it].start))
        }
        return res
    }
}