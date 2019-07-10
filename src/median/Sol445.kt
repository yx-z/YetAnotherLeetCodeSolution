package median

import java.math.BigInteger

class Sol445 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun addTwoNumbers(l1: ListNode, l2: ListNode): ListNode {
        val sb1 = StringBuilder()
        var curr1: ListNode? = l1
        while (curr1 != null) {
            sb1.append(curr1.`val`)
            curr1 = curr1.next
        }
        val sb2 = StringBuilder()
        var curr2: ListNode? = l2
        while (curr2 != null) {
            sb2.append(curr2.`val`)
            curr2 = curr2.next
        }
        val res =
            (BigInteger(sb1.toString()) + BigInteger(sb2.toString())).toString()
        val dummy = ListNode(0)
        var head = dummy
        for (c in res) {
            head.next = ListNode(c - '0')
            head = head.next!!
        }
        return dummy.next!!
    }
}