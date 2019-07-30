package median

class Sol393 {

    fun validUtf8(data: IntArray): Boolean {
        val bins = data.map {
            var bin = it.toString(2)
            val bl = bin.length
            when {
                bl < 8 -> bin = "0".repeat(8 - bl) + bin
                bl > 8 -> bin = bin.substring(bl - 8)
            }
            bin
        }
        var i = 0
        while (i < bins.size) {
            var cur = bins[i]
            if (cur[0] == '1') {
                var j = 1
                while (cur[j] == '1' && j < 5) j++
                if (j == 1 || j == 5) return false
                while (j > 1) {
                    i++
                    if (i == bins.size) return false
                    cur = bins[i]
                    if (cur[0] != '1' || cur[1] != '0') return false
                    j--
                }
            }
            i++
        }
        return true
    }
}