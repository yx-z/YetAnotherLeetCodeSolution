package hard

class Sol146 {

    class LRUCache(val capacity: Int) {

        class MyList {

            class Node(val k: Int) {
                var pre: Node? = null
                var nex: Node? = null
            }

            var size = 0
            val seen = HashMap<Int, Node>()
            val head = Node(-1)
            var tail = head

            fun remove(k: Int) {
                val n = seen[k]!!
                if (n == tail) {
                    tail = tail.pre!!
                }
                n.pre!!.nex = n.nex
                n.nex?.pre = n.pre
                seen.remove(k)
                size--
            }

            fun append(k: Int) {
                tail.nex = Node(k).apply {
                    pre = tail
                    seen[k] = this
                }
                tail = tail.nex!!
                size++
            }

            fun contains(v: Int) = seen.contains(v)

            fun first() = head.nex!!.k
        }

        val data = HashMap<Int, Int>()
        val keys = MyList()

        fun get(key: Int) = if (data.containsKey(key)) {
            keys.remove(key)
            keys.append(key)
            data[key]!!
        } else {
            -1
        }

        fun put(key: Int, value: Int) {
            data[key] = value
            if (keys.contains(key)) {
                keys.remove(key)
            }
            keys.append(key)
            if (keys.size > capacity) {
                val k0 = keys.first()
                data.remove(k0)
                keys.remove(k0)
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