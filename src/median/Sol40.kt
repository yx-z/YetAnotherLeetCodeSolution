package median

class Sol40 {

    fun combinationSum2(
        candidates: IntArray,
        target: Int,
        start: Int = 0
    ): List<List<Int>> {
        if (target == 0) {
            return listOf(emptyList())
        }
        if (target < 0 || start >= candidates.size) {
            return emptyList()
        }

        val first = candidates[start]
        val useFirst =
            combinationSum2(candidates, target - first, start + 1)
        val noFirst = combinationSum2(candidates, target, start + 1)
        return (useFirst.map { it + first } + noFirst).map { it.sorted() }
            .distinct()
    }
}