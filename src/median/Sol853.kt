package median

class Sol853 {

    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        val target = target.toDouble()
        val sorted = position.zip(speed).sortedByDescending { it.first }
        var count = 0
        var pre = 0.0
        sorted.map { (p, s) -> (target - p) / s }.forEach {
            if (it > pre) {
                pre = it
                count++
            }
        }
        return count
    }
}