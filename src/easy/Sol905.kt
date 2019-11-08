package easy

class Sol905 {

    fun sortArrayByParity(A: IntArray): IntArray {
        var lo = 0
        var hi = A.size - 1
        while (lo < hi) {
            while (lo < hi && A[lo] % 2 == 0) lo++
            while (lo < hi && A[hi] % 2 == 1) hi--
            if (lo < hi) swap(A, lo, hi)
        }
        return A
    }

    fun swap(A: IntArray, i: Int, j: Int) {
        val tmp = A[i]
        A[i] = A[j]
        A[j] = tmp
    }
}