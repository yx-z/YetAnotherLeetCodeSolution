package hard

import java.util.*

class Sol23 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    // O(nlog k) w/ heap
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val heap = PriorityQueue<ListNode>(Comparator
        { x, y -> x.`val` - y.`val` })
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

    // O(nlog l) : l = longest list w/ mergesort-like divide and conquer
    fun mergeK(
        lists: Array<ListNode?>,
        start: Int = 0,
        end: Int = lists.size - 1
    ): ListNode? {
        if (lists.isEmpty()) return null
        if (start >= end) return lists[start]

        val mid = start + (end - start) / 2
        val left = mergeK(lists, start, mid)
        val right = mergeK(lists, mid + 1, end)
        return mergeTwoLists(left, right)
    }

    // from Sol21
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        val head = ListNode(0)
        var cur1 = l1
        var cur2 = l2
        var cur = head
        while (cur1 != null && cur2 != null) {
            cur.next = if (cur1.`val` < cur2.`val`) cur1.apply { cur1 = next }
            else cur2.apply { cur2 = next }
            cur = cur.next!!
        }
        cur.next = cur1 ?: cur2
        return head.next
    }
}
