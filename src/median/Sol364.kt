package median

class Sol364 {

    class NestedInt(val int: Int? = null, val list: List<NestedInt>? = null) {
        fun isInt() = int != null

        fun isList() = list != null
    }

    fun sum(list: List<NestedInt>) = sumHelp(list, depthHelp(list))

    fun depthHelp(list: List<NestedInt>): Int = list.filter { it.isList() }
        .map { depthHelp(it.list!!) + 1 }.max() ?: 1

    fun sumHelp(list: List<NestedInt>, depth: Int): Int = list.map {
        when {
            it.isInt() -> depth * it.int!!
            it.isList() -> sumHelp(it.list!!, depth - 1)
            else -> 0
        }
    }.sum()
}