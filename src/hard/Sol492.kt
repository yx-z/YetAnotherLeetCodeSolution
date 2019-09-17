package hard

class Sol492 {

    class AllOne {
        val freqs = DLL(0)
        val inv = HashMap<Int, DLL<String>>()
        val freq = HashMap<String, Int>()

        fun inc(key: String) {
            val f = (freq[key] ?: 0) + 1
            freq[key] = f
            freqs.addAfter(f, f - 1)
            if (f > 1) {
                // remove old
                inv[f - 1]!!.remove(key)
                if (inv[f - 1]!!.isEmpty()) freqs.remove(f - 1)
            }
            if (f !in inv) inv[f] = DLL("")
            inv[f]!!.addAfter(key)
        }

        fun dec(key: String) {
            if (key !in freq) return
            val f = freq[key]!! - 1
            freq[key] = f
            freqs.addBefore(f, f + 1)
            if (f > 0) {
                // add new
                if (f !in inv) inv[f] = DLL("")
                inv[f]!!.addAfter(key)
            }
            inv[f + 1]!!.remove(key)
            if (inv[f + 1]!!.isEmpty()) freqs.remove(f + 1)
        }

        fun getMaxKey() = if (freqs.isEmpty()) ""
        else inv[freqs.last()]!!.first()

        fun getMinKey() = if (freqs.isEmpty()) ""
        else inv[freqs.first()]!!.first()
    }

    // Doubly Linked List
    class DLL<T>(val empty: T) {

        class Node<T>(
            val value: T,
            var prev: Node<T>? = null,
            var next: Node<T>? = null
        )

        val head = Node(empty)
        var tail = head
        val lookup = HashMap<T, Node<T>>().apply { put(empty, head) }

        fun isEmpty() = head == tail

        fun remove(k: T) {
            if (k !in lookup) return
            val n = lookup[k]!!
            if (n == tail) tail = tail.prev!!
            n.prev!!.next = n.next
            n.next?.prev = n.prev
            lookup.remove(k)
        }

        fun addAfter(k: T, after: T = tail.value) {
            if (k in lookup || after !in lookup) return
            val preNode = lookup[after]!!
            val nexNode = preNode.next
            val n = Node(k, preNode, nexNode).apply { lookup[k] = this }
            preNode.next = n
            nexNode?.prev = n
            if (preNode == tail) tail = n
        }

        fun addBefore(k: T, before: T) {
            if (k in lookup || before !in lookup) return
            val nexNode = lookup[before]!!
            val preNode = nexNode.prev!!
            val n = Node(k, preNode, nexNode).apply { lookup[k] = this }
            preNode.next = n
            nexNode.prev = n
        }

        fun first() = head.next!!.value

        fun last() = tail.value
    }
}