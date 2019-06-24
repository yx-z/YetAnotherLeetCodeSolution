package median

class Sol86 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun partition(head: ListNode?, x: Int): ListNode? {
        val small = ListNode(0)
        var smallTail = small
        val big = ListNode(0)
        var bigTail = big
        var curr = head
        while (curr != null) {
            if (curr.`val` < x) {
                smallTail.next = curr
                smallTail = smallTail.next!!
            } else {
                bigTail.next = curr
                bigTail = bigTail.next!!
            }
            curr = curr.next
        }

        smallTail.next = big.next
        return small.next
    }
}