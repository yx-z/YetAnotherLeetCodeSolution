package median

class Sol328 {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun oddEvenList(head: ListNode?): ListNode? {
        val odd = ListNode(0)
        val even = ListNode(0)
        var oddCurr = odd
        var evenCurr = even
        var curr = head
        while (curr != null) {
            oddCurr.next = curr
            oddCurr = oddCurr.next!!
            curr = curr.next
            if (curr == null) {
                break
            } else {
                evenCurr.next = curr
                evenCurr = evenCurr.next!!
                curr = curr.next
            }
        }
        oddCurr.next = even.next
        evenCurr.next = null
        return odd.next
    }
}