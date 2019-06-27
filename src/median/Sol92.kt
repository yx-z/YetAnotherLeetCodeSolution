package median

class Sol92 {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        override fun toString() = `val`.toString() + "," + next.toString()
    }

    fun reverseBetween(head: ListNode?, m: Int, n: Int): ListNode? {
        if (m == n) {
            return head
        }

        val dummy = ListNode(0).apply { next = head }
        var mCount = m
        var lSentinel = dummy
        while (mCount > 1) {
            lSentinel = lSentinel.next!!
            mCount--
        }
        val lReverse = lSentinel.next!!
        var nCount = n
        var rReverse = head!!
        while (nCount > 1) {
            rReverse = rReverse.next!!
            nCount--
        }
        val rSentinel = rReverse.next

        var lPre = lReverse
        var lPointer = lReverse.next
        while (lPointer !== rSentinel) {
            val lNext = lPointer!!.next
            lPointer.next = lPre
            lPre = lPointer
            lPointer = lNext
        }

        lSentinel.next = rReverse
        lReverse.next = rSentinel
        return dummy.next
    }
}