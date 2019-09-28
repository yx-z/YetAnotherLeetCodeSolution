package median

class Sol77 {

    fun combine(n: Int, k: Int): List<List<Int>> = when {
        k == 1 -> (1..n).map { mutableListOf(it) }
        n < k -> emptyList()
        else -> combine(n - 1, k) + combine(n - 1, k - 1)
            .map { (it as MutableList<Int>).apply { add(n) } }
    }
}