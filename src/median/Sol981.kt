package median

class Sol981 {

    /**
     * Your TimeMap object will be instantiated and called as such:
     * var obj = TimeMap()
     * obj.set(key,value,timestamp)
     * var param_2 = obj.get(key,timestamp)
     */
    class TimeMap {
        /** Initialize your data structure here. */
        val keyVal = HashMap<String, ArrayList<Pair<Int, String>>>()

        fun set(key: String, value: String, timestamp: Int) {
            if (key !in keyVal) keyVal[key] = ArrayList()
            keyVal[key]!!.add(timestamp to value)
        }

        fun get(key: String, timestamp: Int): String {
            val l = keyVal[key] ?: return ""
            val i = l.binarySearch { it.first - timestamp }
            return when {
                i >= 0 -> l[i].second
                i < -1 -> l[-i - 2].second
                else -> ""
            }
        }
    }
}