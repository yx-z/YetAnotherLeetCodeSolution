package easy

class Sol21 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

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