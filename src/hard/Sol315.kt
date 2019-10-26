package hard

class Sol315 {

    fun countSmaller(ns: IntArray): List<Int> {
        val smaller = ArrayList<Int>().apply { repeat(ns.size) { this.add(0) } }
        fun mergeSort(ps: MutableList<IndexedValue<Int>>): List<IndexedValue<Int>> {
            if (ps.size <= 1) return ps
            val mid = ps.size / 2
            val left = mergeSort(ArrayList(ps).subList(0, mid))
            val right = mergeSort(ArrayList(ps).subList(mid, ps.size))
            var i = 0
            var j = 0
            while (i < left.size || j < right.size) {
                if (j == right.size || i < left.size && left[i].value <= right[j].value) {
                    ps[i + j] = left[i]
                    smaller[left[i].index] += j
                    i++
                } else {
                    ps[i + j] = right[j]
                    j++
                }
            }
            return ps
        }
        mergeSort(ns.withIndex().toMutableList())
        return smaller
    }
}