package hard

import java.util.*

class Sol146 {

    class LRUCache(val capacity: Int) {
        val data = HashMap<Int, Int>()
        val keys = LinkedList<Int>()

        fun get(key: Int) = if (keys.contains(key)) {
            keys.remove(key)
            keys.add(key)
            data[key]!!
        } else {
            -1
        }

        fun put(key: Int, value: Int) {
            data[key] = value
            if (keys.contains(key)) {
                keys.remove(key)
            }
            keys.add(key)
            if (keys.size > capacity) {
                data.remove(keys[0])
                keys.removeAt(0)
            }
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * var obj = LRUCache(capacity)
     * var param_1 = obj.get(key)
     * obj.put(key,value)
     */
}