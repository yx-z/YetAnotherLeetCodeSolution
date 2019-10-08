package median

import java.util.*
import kotlin.math.abs

class Sol166 {

    fun fractionToDecimal(numerator: Int, denominator: Int): String {
        if (numerator == 0) return "0"
        val res = StringBuilder()
        if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0))
            res.append('-')
        var num = abs(numerator.toLong())
        val den = abs(denominator.toLong())

        res.append(num / den)
        num %= den
        if (num == 0L) return res.toString()

        res.append('.')
        val map = HashMap<Long, Int>()
        map[num] = res.length
        while (num != 0L) {
            num *= 10
            res.append(num / den)
            num %= den
            if (num in map) {
                res.insert(map[num]!!, '(')
                res.append(')')
                break
            } else {
                map[num] = res.length
            }
        }
        return res.toString()
    }
}