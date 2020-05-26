package median

class Sol29 {

	fun divide(dividend: Int, divisor: Int): Int {
		val sign = if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) -1 else 1
		val ldividend = Math.abs(dividend.toLong())
		val ldivisor = Math.abs(divisor.toLong())
		if (ldivisor == 0L) return Int.MAX_VALUE
		if ((ldividend == 0L) || (ldividend < ldivisor)) return 0
		val lans = ldivide(ldividend, ldivisor)
		return when {
			lans > Int.MAX_VALUE -> if (sign == 1) Int.MAX_VALUE else Int.MIN_VALUE
			else -> (sign * lans).toInt()
		}
	}

	private fun ldivide(ldividend: Long, ldivisor: Long): Long {
		if (ldividend < ldivisor) return 0
		var sum = ldivisor
		var multiple: Long = 1
		while (sum + sum <= ldividend) {
			sum += sum
			multiple += multiple
		}
		return multiple + ldivide(ldividend - sum, ldivisor)
	}
}