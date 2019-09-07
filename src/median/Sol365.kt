package median

class Sol365 {
    fun canMeasureWater(x: Int, y: Int, z: Int): Boolean {
        if (x + y < z) return false
        return if (x == z || y == z || x + y == z) true else z % gcd(x, y) == 0
    }

    fun gcd(a: Int, b: Int): Int {
        var a = a
        var b = b
        while (b != 0) {
            val temp = b
            b = a % b
            a = temp
        }
        return a
    }
}