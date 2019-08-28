package hard

class Sol460 {

    class LFUCache(val capacity: Int) {

        // O(1) to append and remove a given key
        // code from my Problem 146 - LRUCache
        private class DoublyLinkedList {

            private class Node(val value: Int, var prev: Node? = null) {
                var next: Node? = null
            }

            private val lookup = HashMap<Int, Node>()
            private val head = Node(-1)
            private var tail = head
            private var size = 0

            /**
             * get should be public for `size` but not set
             */
            fun size() = size

            /**
             * remove a node given its key
             */
            fun remove(k: Int) {
                val n = lookup[k]!!
                if (n == tail) {
                    tail = tail.prev!!
                }
                n.prev!!.next = n.next
                n.next?.prev = n.prev
                lookup.remove(k)
                size--
            }

            /**
             * append a node binded with its key
             */
            fun append(k: Int) {
                tail.next = Node(k, tail).apply { lookup[k] = this }
                tail = tail.next!!
                size++
            }

            operator fun contains(v: Int) = v in lookup

            fun first() = head.next!!.value
        }

        private val data = HashMap<Int, Int>() // <key, value>
        private val freq = HashMap<Int, Int>() // <key, count>
        // <count, doubly linked list in the order of recent use>
        private val lru = HashMap<Int, DoublyLinkedList>()
        private var minFreq = 0

        /**
         * update freq of key in lru and update minFreq if necessary
         */
        private fun updateKeyFreq(key: Int) {
            val oldCount = freq[key]!!
            val newCount = oldCount + 1
            lru[oldCount]!!.remove(key)
            if (lru[oldCount]!!.size() == 0 && minFreq == oldCount) {
                minFreq = newCount
            }
            freq[key] = newCount
            if (newCount !in lru) {
                lru[newCount] = DoublyLinkedList()
            }
            lru[newCount]!!.append(key)
        }

        fun get(key: Int) = if (key in data) {
            updateKeyFreq(key)
            data[key]!!
        } else {
            -1
        }

        fun put(key: Int, value: Int) {
            if (key in data) {
                data[key] = value
                updateKeyFreq(key)
            } else {
                if (capacity == 0) {
                    return
                }
                if (data.size == capacity) {
                    val removeKey = lru[minFreq]!!.first()
                    lru[minFreq]!!.remove(removeKey)
                    data.remove(removeKey)
                }
                minFreq = 1
                data[key] = value
                freq[key] = 1
                if (1 !in lru) {
                    lru[1] = DoublyLinkedList()
                }
                lru[1]!!.append(key)
            }
        }
    }
}