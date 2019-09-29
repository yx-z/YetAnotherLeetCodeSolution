package median

class Sol151 {

    fun reverseWords(s: String) = s.trim().split(' ')
        .filter { it.isNotEmpty() }.reversed().joinToString(" ")
}