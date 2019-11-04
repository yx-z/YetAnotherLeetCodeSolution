package median

import java.util.*

class Sol911 {

    class TopVotedCandidate(persons: IntArray, times: IntArray) {
        // <time, top id>
        val topCandidates = TreeMap<Int, Int>()

        init {
            // <id, freq>
            val freq = HashMap<Int, Int>()
            var maxFreq = 0
            for ((i, t) in times.withIndex()) {
                // id got a vote at time t
                val id = persons[i]
                freq[id] = (freq[id] ?: 0) + 1
                if (freq[id]!! >= maxFreq) {
                    maxFreq = freq[id]!!
                    topCandidates[t] = id
                }
            }
        }

        fun q(t: Int) = topCandidates.floorEntry(t).value
    }
}