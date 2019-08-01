package median

import java.util.*

class Sol347 {

    fun topKFrequent(nums: IntArray, k: Int): List<Int> {
        val f = nums.fold(HashMap<Int, Int>()) { m, i ->
            m[i] = 1 + (m[i] ?: 0)
            m
        }
        val q = PriorityQueue<Map.Entry<Int, Int>>(
            Comparator { (_, f1), (_, f2) -> f2 - f1 })
            .apply { addAll(f.entries) }
        return (1..k).map { q.poll().key }
    }
}