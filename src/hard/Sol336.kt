package hard


class Sol336 {

    fun palindromePairs(words: Array<String>): List<List<Int>> {
        val idx = words.indices.map { words[it] to it }.toMap()
        val res = ArrayList<List<Int>>()
        val isPal = { s: String -> s.reversed() == s }

        if ("" in idx.keys) {
            val empty = idx.getValue("")
            for (i in 0 until words.size) {
                if (isPal(words[i]) && i != empty) {
                    res.add(listOf(empty, i))
                    res.add(listOf(i, empty))
                }
            }
        }

        for (i in 0 until words.size) {
            val rev = words[i].reversed()
            if (rev in idx.keys) {
                val found = idx.getValue(rev)
                if (found != i) res.add(listOf(i, found))
            }
        }

        for (i in 0 until words.size) {
            val cur = words[i]
            for (cut in 1 until cur.length) {
                if (isPal(cur.substring(0, cut))) {
                    val rev = cur.substring(cut).reversed()
                    if (rev in idx.keys) {
                        val found = idx.getValue(rev)
                        if (found != i) res.add(listOf(found, i))
                    }
                }
                if (isPal(cur.substring(cut))) {
                    val rev = cur.substring(0, cut).reversed()
                    if (rev in idx.keys) {
                        val found = idx.getValue(rev)
                        if (found != i) res.add(listOf(i, found))
                    }
                }
            }
        }

        return res
    }
}