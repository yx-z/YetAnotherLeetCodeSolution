package median

class Sol93 {

    fun restoreIpAddresses(str: String, split: Int = 4): List<String> {
        if (str.isEmpty()) return emptyList()
        if (split == 1) return if (validPart(str)) listOf(str) else emptyList()
        val res = ArrayList<String>()
        for (len in 1..3) {
            if (len <= str.length) {
                val part = str.substring(0, len)
                if (validPart(part)) {
                    val rem = restoreIpAddresses(str.substring(len), split - 1)
                    if (rem.isNotEmpty()) res.addAll(rem.map { "$part.$it" })
                }
            }
        }
        return res
    }

    fun validPart(str: String): Boolean {
        val num = str.toIntOrNull() ?: return false
        return (num == 0 && str == "0") || (num in 1..255 && str[0] != '0')
    }
}