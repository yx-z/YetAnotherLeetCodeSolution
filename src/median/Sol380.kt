package median

import java.util.*

class Sol380 {

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * var obj = RandomizedSet()
     * var param_1 = obj.insert(`val`)
     * var param_2 = obj.remove(`val`)
     * var param_3 = obj.getRandom()
     */
    class RandomizedSet {

        /** Initialize your data structure here. */
        val map = HashMap<Int, Int>() // <value, index in list>
        val list = ArrayList<Int>()

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        fun insert(v: Int): Boolean {
            if (v in map) return false
            map[v] = list.size
            list.add(v)
            return true
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        fun remove(v: Int): Boolean {
            if (v !in map) return false
            val oldIdx = map.remove(v)!!
            val last = list.removeAt(list.size - 1)
            if (last != v) {
                list[oldIdx] = last
                map[last] = oldIdx
            }
            return true
        }

        /** Get a random element from the set. */
        fun getRandom() = list[(0 until list.size).random()]
    }
}