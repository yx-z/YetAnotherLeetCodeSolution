package hard

class Sol273 {

    val LESS = arrayOf(
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six",
        "Seven",
        "Eight",
        "Nine",
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen"
    )
    val TENS = arrayOf(
        "Ten",
        "Twenty",
        "Thirty",
        "Forty",
        "Fifty",
        "Sixty",
        "Seventy",
        "Eighty",
        "Ninety"
    )
    val THOUSANDS = arrayOf(" ", "Thousand ", "Million ", "Billion ")

    fun numberToWords(num: Int): String {
        if (num == 0) return "Zero"
        val sb = StringBuilder()
        var n = num
        var i = 0
        while (n > 0) {
            if (n % 1000 != 0) sb.insert(0, f(n % 1000) + THOUSANDS[i])
            n /= 1000
            i++
        }
        return sb.toString().trim()
    }

    private fun f(num: Int): String = when {
        num == 0 -> ""
        num < 20 -> LESS[num - 1] + " "
        num < 100 -> TENS[num / 10 - 1] + " " + f(num % 10)
        else -> LESS[num / 100 - 1] + " Hundred " + f(num % 100)
    }
}