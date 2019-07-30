package median

class Sol12 {

    var M = arrayOf("", "M", "MM", "MMM")
    var C = arrayOf("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    var X = arrayOf("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    var I = arrayOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")

    // tbh, this is more like a linguistic question
    fun intToRoman(n: Int) =
        M[n / 1000] + C[(n % 1000) / 100] + X[(n % 100) / 10] + I[n % 10]
}