package median

class Sol77 {

    fun combine(n: Int, k: Int): List<List<Int>> {
        if (k == 1) return (1..n).map { mutableListOf(it) }
        if (n < k) return emptyList()
        return combine(n - 1, k) + combine(n - 1, k - 1)
            .map { (it as MutableList<Int>).apply { add(n) } }
    }
}