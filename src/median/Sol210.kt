package median

class Sol210 {

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val inG = Array(numCourses) { HashSet<Int>() }
        val outG = Array(numCourses) { HashSet<Int>() }
        prerequisites.forEach { (suc, pre) ->
            inG[suc].add(pre)
            outG[pre].add(suc)
        }
        val res = ArrayList<Int>()
        val roots = inG.indices.filter { inG[it].size == 0 }.toMutableList()
        while (roots.isNotEmpty()) {
            val copy = roots.toList()
            while (roots.isNotEmpty()) res.add(roots.removeAt(0))
            copy.forEach { pre ->
                outG[pre].forEach { suc ->
                    inG[suc].remove(pre)
                    if (inG[suc].size == 0) roots.add(suc)
                }
            }
        }
        return if (res.size == numCourses) res.toIntArray() else IntArray(0)
    }
}
