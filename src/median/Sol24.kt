package median

class Sol24 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }

        val second = head.next
        val swapped = swapPairs(second!!.next)
        second.next = head
        head.next = swapped
        return second
    }
}