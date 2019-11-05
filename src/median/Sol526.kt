package median

class Sol526 {

    fun countArrangement(N: Int): Int {
        var count = 0
        val used = BooleanArray(N + 1)
        fun help(i: Int = 1) {
            if (i > N) {
                count++
            } else {
                (1..N).filter { !used[it] && (it % i == 0 || i % it == 0) }
                    .forEach {
                        used[it] = true
                        help(i + 1)
                        used[it] = false
                    }
            }
        }
        help()
        return count
    }
}