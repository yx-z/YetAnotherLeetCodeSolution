package median

class Sol207 {

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val G = Array(numCourses) { ArrayList<Int>() }
        prerequisites.forEach { (suc, pre) -> G[pre].add(suc) }
        val active = HashSet<Int>()
        fun dfs(start: Int): Boolean {
            if (start in active) return false
            active.add(start)
            return G[start].all { dfs(it) }.also { active.remove(start) }
        }
        return G.indices.all { dfs(it) }
    }
}