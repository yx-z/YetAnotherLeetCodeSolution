package hard

class Sol146 {

    /**
     * Your LRUCache object will be instantiated and called as such:
     * var obj = LRUCache(capacity)
     * var param_1 = obj.get(key)
     * obj.put(key,value)
     */
    class LRUCache(val capacity: Int) {

        // doubly linked list of int that takes O(1) to append and remove
        private class MyList {

            private class Node(val k: Int, var pre: Node? = null) {
                var nex: Node? = null
            }

            var size = 0
            private val seen = HashMap<Int, Node>()
            private val head = Node(-1)
            private var tail = head

            fun remove(k: Int) {
                val n = seen[k]!!
                if (n == tail) tail = tail.pre!!
                n.pre!!.nex = n.nex
                n.nex?.pre = n.pre
                seen.remove(k)
                size--
            }

            fun append(k: Int) {
                tail.nex = Node(k, tail).apply { seen[k] = this }
                tail = tail.nex!!
                size++
            }

            operator fun contains(v: Int) = v in seen

            fun first() = head.nex!!.k
        }

        private val data = HashMap<Int, Int>()
        private val keys = MyList()

        fun get(key: Int) = if (key in data) {
            keys.remove(key)
            keys.append(key)
            data[key]!!
        } else -1

        fun put(key: Int, value: Int) {
            data[key] = value
            if (key in keys) keys.remove(key)
            keys.append(key)
            if (keys.size > capacity) {
                val k0 = keys.first()
                data.remove(k0)
                keys.remove(k0)
            }
        }
    }
}