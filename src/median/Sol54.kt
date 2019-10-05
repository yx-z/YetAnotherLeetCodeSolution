package median

class Sol54 {

    fun spiralOrder(
        m: Array<IntArray>,
        rows: IntRange = m.indices,
        cols: IntRange = if (m.isEmpty()) IntRange.EMPTY else m[0].indices
    ): List<Int> {
        val res = ArrayList<Int>()
        when {
            rows.len <= 0 || cols.len <= 0 -> {
            }
            rows.len == 1 -> {
                val (s, e) = cols
                for (i in s..e) res.add(m[rows.first][i])
            }
            cols.len == 1 -> {
                val (s, e) = rows
                for (i in s..e) res.add(m[i][cols.first])
            }
            else -> {
                val (rs, re) = rows
                val (cs, ce) = cols
                val inner = spiralOrder(m, rs + 1 until re, cs + 1 until ce)
                for (i in cs..ce) res.add(m[rs][i])
                for (i in rs + 1..re) res.add(m[i][ce])
                for (i in ce - 1 downTo cs) res.add(m[re][i])
                for (i in re - 1 downTo rs + 1) res.add(m[i][cs])
                res.addAll(inner)
            }
        }
        return res
    }

    private val IntRange.len: Int get() = endInclusive - start + 1
    operator fun IntRange.component1() = start
    operator fun IntRange.component2() = endInclusive
}