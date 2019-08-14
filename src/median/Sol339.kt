package median

class Sol339 {

    class NestedInt(val int: Int? = null, val list: List<NestedInt>? = null) {
        fun isInt() = int != null

        fun isList() = list != null
    }

    fun sum(list: List<NestedInt>, depth: Int = 1): Int = list.map {
        when {
            it.isInt() -> depth * it.int!!
            it.isList() -> sum(it.list!!, depth + 1)
            else -> 0
        }
    }.sum()
}

