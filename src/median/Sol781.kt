package median

class Sol781 {

    fun numRabbits(ans: IntArray) = ans.map { it + 1 }.groupBy { it }
        .mapValues { it.value.size }.map { (size, freq) ->
            if (freq % size == 0) freq else (freq / size + 1) * size
        }.sum()
}