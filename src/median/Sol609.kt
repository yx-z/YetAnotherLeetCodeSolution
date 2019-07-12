package median

class Sol609 {

    fun findDuplicate(paths: Array<String>): List<List<String>> {
        // <content, path>
        val map = HashMap<String, ArrayList<String>>()
        paths.forEach {
            val files = it.split(" ")
            val path = files[0]
            for (i in 1 until files.size) {
                val file = files[i]
                val content = file.substring(
                    file.indexOf("(") + 1,
                    file.indexOf(")")
                )
                if (!map.containsKey(content)) {
                    map[content] = ArrayList()
                }
                map[content]!!.add(
                    "$path/" + file.substring(0, file.indexOf("("))
                )
            }
        }
        return map.filter { (_, v) -> v.size >= 2 }.values.toList()
    }
}