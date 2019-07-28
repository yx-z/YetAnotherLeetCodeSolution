package hard

class Sol68 {

    fun fullJustify(str: Array<String>, width: Int): List<String> {
        val each = ArrayList<ArrayList<String>>()
        var i = 0
        while (i < str.size) {
            val ls = ArrayList<String>().apply { add(str[i]) }
            var len = str[i].length
            while (i + 1 < str.size && len + str[i + 1].length < width) {
                i++
                ls.add(str[i])
                len += 1 + str[i].length
            }
            each.add(ls)
            i++
        }

        val res = ArrayList<String>()
        for (j in 0 until each.size - 1) {
            val ws = each[j]
            val sb = StringBuilder().apply { append(ws[0]) }
            if (ws.size == 1) {
                sb.append(" ".repeat(width - sb.length))
            } else {
                val lenSpace = width - ws.sumBy { it.length }
                val numSpace = lenSpace / (ws.size - 1)
                if (lenSpace % (ws.size - 1) == 0) {
                    for (k in 1 until ws.size) {
                        sb.append(" ".repeat(numSpace))
                        sb.append(ws[k])
                    }
                } else {
                    // find x : (numSpace + 1) * x +
                    //          (words.size - 1 - x) * numSpace = lenSpace
                    val x = lenSpace - (ws.size - 1) * numSpace
                    println(x)
                    for (k in 1..x) {
                        sb.append(" ".repeat(numSpace + 1))
                        sb.append(ws[k])
                    }
                    for (k in x + 1 until ws.size) {
                        sb.append(" ".repeat(numSpace))
                        sb.append(ws[k])
                    }
                }
            }
            res.add(sb.toString())
        }

        val sb = StringBuilder()
        sb.append(each.last().joinToString(" "))
            .append(" ".repeat(width - sb.length))
        res.add(sb.toString())

        return res
    }
}