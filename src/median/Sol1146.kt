package median

import java.util.*

class Sol1146 {

    class SnapshotArray(length: Int) {
        var snapId = 0
        // [<snapId, value>]
        val A = Array(length) { TreeMap<Int, Int>().apply { put(0, 0) } }

        fun set(index: Int, `val`: Int) {
            A[index][snapId] = `val`
        }

        fun snap(): Int {
            snapId++
            return snapId - 1
        }

        fun get(index: Int, snap_id: Int) = A[index].floorEntry(snap_id).value
    }
}