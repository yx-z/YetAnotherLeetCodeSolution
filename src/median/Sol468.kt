package median

class Sol468 {

    fun validIPAddress(t: String): String {
        val ipv4 = { s: String ->
            try {
                val i = s.toInt()
                i in 0..255 && i.toString() == s
            } catch (_: NumberFormatException) {
                false
            }
        }
        if (t.count { it == '.' } == 3 && t.split(".").all(ipv4)) return "IPv4"
        val ipv6 = { s: String ->
            if (s.length > 4) false
            else {
                try {
                    s.toInt(16) >= 0 && s[0] != '-'
                } catch (_: NumberFormatException) {
                    false
                }
            }
        }
        return if (t.count { it == ':' } == 7 && t.split(":").all(ipv6)) "IPv6"
        else "Neither"
    }
}