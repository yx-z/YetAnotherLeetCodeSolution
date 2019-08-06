package hard

class Sol25 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        override fun toString() = "$`val`,$next"
    }

    // 1 -> 2 -> 3 -> 4 -> 5 => 2 -> 1 -> 4 -> 3 -> 5
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (k > head.len()) return head
        var curEnd: ListNode? = head
        for (i in 1 until k) curEnd = curEnd!!.next!!
        var curStart = curEnd!!.next
        val (ret, tmp) = rev(head!!, curEnd)
        var preEnd = tmp
        preEnd.next = curStart
        w@ while (curStart != null) {
            curEnd = curStart
            for (i in 1 until k) {
                if (curEnd!!.next == null) break@w
                curEnd = curEnd.next!!
            }
            val nex = curEnd!!.next
            val (h, t) = rev(curStart, curEnd)
            t.next = nex
            preEnd.next = h
            preEnd = t
            curStart = nex
        }
        return ret
    }

    // 1 -> 2 -> 3    => 3    -> 2 -> 1    -> null
    // head      tail    tail         head    enforce head.next = null
    fun rev(head: ListNode, tail: ListNode): Pair<ListNode, ListNode> {
        val end = tail.next
        val dummy = ListNode(0)
        dummy.next = head
        var pre = dummy
        var cur: ListNode? = head
        while (cur != end) {
            val nex = cur!!.next
            cur.next = pre
            pre = cur
            cur = nex
        }
        head.next = null
        return tail to head
    }

    fun ListNode?.len(): Int = if (this == null) 0 else 1 + next.len()
}