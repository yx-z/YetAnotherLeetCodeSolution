package median

import kotlin.math.min

class Sol64 {

    fun minPathSum(G: Array<IntArray>): Int {
        val (m, n) = G.size to G[0].size
        val M = Array(m) { r -> IntArray(n) { c -> G[r][c] } }
        for (r in m - 2 downTo 0) M[r][n - 1] += M[r + 1][n - 1]
        for (c in n - 2 downTo 0) M[m - 1][c] += M[m - 1][c + 1]
        for (r in m - 2 downTo 0) for (c in n - 2 downTo 0)
            M[r][c] += min(M[r + 1][c], M[r][c + 1])
        return M[0][0]
    }
}