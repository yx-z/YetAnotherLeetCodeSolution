package easy

class Sol203 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        if (head == null) {
            return head
        }

        return if (head.`val` == `val`) {
            removeElements(head.next, `val`)
        } else {
            head.next = removeElements(head.next, `val`)
            head
        }
    }
}