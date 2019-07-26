package median

class Sol900Kt {

    class RLEIterator(val A: IntArray) {
        val size = A.size
        var idx = 0

        fun next(n: Int): Int {
            var x = n
            while (idx < size && A[idx] < x) {
                x -= A[idx]
                idx += 2
            }
            if (idx >= size) {
                return -1
            }
            A[idx] -= x
            return A[idx + 1]
        }
    }
}