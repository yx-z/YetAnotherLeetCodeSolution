package median

class Sol609 {

    fun findDuplicate(paths: Array<String>) = paths.flatMap {
        it.split(" ").run {
            takeLast(size - 1).map { file ->
                file.substring(
                    file.indexOf("(") + 1,
                    file.indexOf(")")
                ) to "${this[0]}/" + file.substring(0, file.indexOf("("))
            }
        }
    }
        .groupBy { it.first }.values
        .filter { it.size >= 2 }
        .map { it.map { it.second } }
}