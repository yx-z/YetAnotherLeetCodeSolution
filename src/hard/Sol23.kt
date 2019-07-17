package hard

import java.util.*

class Sol23 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val heap =
            PriorityQueue<ListNode>(Comparator { x, y -> x.`val` - y.`val` })
        lists.filterNotNull().forEach { heap.add(it) }
        val head = ListNode(0)
        var curr: ListNode = head
        while (heap.isNotEmpty()) {
            val node = heap.remove()
            curr.next = node
            curr = curr.next!!
            if (node.next != null) heap.add(node.next)
        }
        return head.next
    }
}