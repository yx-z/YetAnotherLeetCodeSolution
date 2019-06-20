package median

class Sol2 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?, carry: Int = 0): ListNode? {
        if (l1 == null && l2 == null) {
            return if (carry == 0) {
                null
            } else {
                ListNode(1)
            }
        }
        if (l1 == null || l2 == null) {
            val nonnull = l1 ?: l2!!
            return if (carry == 0) {
                nonnull
            } else { // carry == 1
                if (nonnull.`val` < 9) {
                    nonnull.`val` += 1
                    nonnull
                } else {
                    val nex = addTwoNumbers(null, nonnull.next, carry)
                    nonnull.next = nex
                    nonnull.`val` = 0
                    nonnull
                }
            }
        }

        val sum = l1.`val` + l2.`val` + carry
        val nex = addTwoNumbers(l1.next, l2.next, sum / 10)
        val res = ListNode(sum % 10)
        res.next = nex
        return res
    }
}