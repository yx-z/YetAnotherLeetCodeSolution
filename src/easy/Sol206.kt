package easy

class Sol206 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverseList1(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }

        val nex = head.next!!
        val newHead = reverseList1(nex)
        nex.next = head
        head.next = null
        return newHead
    }

    fun reverseList2(head: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur = head
        while (cur != null) {
            val nex = cur.next
            cur.next = pre
            pre = cur
            cur = nex
        }
        return pre
    }
}

