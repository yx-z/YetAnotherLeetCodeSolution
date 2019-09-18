package median

class Sol452 {

    fun findMinArrowShots(points: Array<IntArray>): Int {
        points.sortBy { it[1] }
        var i = 0
        var count = 0
        while (i < points.size) {
            val curEnd = points[i][1]
            while (i + 1 < points.size && points[i + 1][0] <= curEnd) i++
            count++
            i++
        }
        return count
    }
}