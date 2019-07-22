package easy

class Sol482 {

    fun licenseKeyFormatting(S: String, K: Int) = S.toUpperCase()
        .replace("-", "").reversed().withIndex().groupBy { it.index / K }
        .map { it.value.map { it.value }.joinToString("") }.joinToString("-")
        .reversed()
}