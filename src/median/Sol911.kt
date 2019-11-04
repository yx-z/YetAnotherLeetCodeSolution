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
            for ((i, t) in persons.zip(times)) {
                // i got a vote at time t
                freq[i] = (freq[i] ?: 0) + 1
                if (freq[i]!! >= maxFreq) {
                    maxFreq = freq[i]!!
                    topCandidates[t] = i
                }
            }
        }

        fun q(t: Int) = topCandidates.floorEntry(t).value
    }
}