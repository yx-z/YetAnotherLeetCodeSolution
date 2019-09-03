package median

import java.lang.Integer.max

class Sol253 {
    fun minMeetingRooms(ints: Array<IntArray>): Int {
        val starts = ints.map { it[0] }.sorted()
        val ends = ints.map { it[1] }.sorted()
        var startIdx = 0
        var endIdx = 0
        var maxRooms = 0
        var curRooms = 0
        while (startIdx < starts.size) {
            if (starts[startIdx] < ends[endIdx]) {
                startIdx++
                curRooms++
                maxRooms = max(maxRooms, curRooms)
            } else {
                endIdx++
                curRooms--
            }
        }
        return maxRooms
    }
}
