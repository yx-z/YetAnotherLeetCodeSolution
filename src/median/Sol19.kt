package median

class Sol19 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    // [d,1,2,n],2
    //  s   f
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head

        var slow = dummy
        var fast = dummy
        for (i in 1..n) {
            fast = fast.next!!
        }
        while (fast.next != null) {
            slow = slow.next!!
            fast = fast.next!!
        }
        slow.next = slow.next!!.next
        return dummy.next
    }
}