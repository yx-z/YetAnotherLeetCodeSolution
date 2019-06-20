package median

class Sol49 {

    fun groupAnagrams(strs: Array<String>) =
        strs.groupBy { it.toCharArray().sorted().toString() }.values.toList()
}