package median

import java.util.*

class Sol43 {

    // 123 * 450 = 123 * 4 * 100 + 123 * 5 * 10 + 123 * 0 * 1
    fun multiply(num1: String, num2: String) =
        if (num1 == "0" || num2 == "0") "0"
        else num2.indices.map {
            (num1 * num2[it]).apply { this timesTenTo num2.length - it - 1 }
        }.reduce { l1, l2 -> l1.apply { l1 + l2 } }.joinToString("")

    // 123 * 4
    private operator fun String.times(c: Char): LinkedList<Int> {
        val digit2 = c - '0'
        if (digit2 == 0) return LinkedList<Int>().apply { add(0) }
        val res = LinkedList<Int>()
        var carry = 0
        for (i in reversed()) {
            val digit1 = i - '0'
            val prod = digit1 * digit2 + carry
            res.addFirst(prod.rem(10))
            carry = prod / 10
        }
        if (carry > 0) res.addFirst(carry)
        return res
    }

    // 123 * 100
    private infix fun LinkedList<Int>.timesTenTo(n: Int) {
        for (i in 1..n) add(0)
    }

    // add `that` to `this`
    private operator fun LinkedList<Int>.plus(that: LinkedList<Int>) {
        var carry = 0
        var i = size - 1
        var j = that.size - 1
        while (i >= 0 && j >= 0) {
            val sum = this[i] + that[j] + carry
            this[i] = sum.rem(10)
            carry = sum / 10
            i--
            j--
        }
        if (i >= 0) {
            while (i >= 0) {
                val sum = this[i] + carry
                this[i] = sum.rem(10)
                carry = sum / 10
                i--
            }
        } else {
            while (j >= 0) {
                val sum = that[j] + carry
                addFirst(sum.rem(10))
                carry = sum / 10
                j--
            }
        }
        if (carry > 0) addFirst(carry)
    }
}