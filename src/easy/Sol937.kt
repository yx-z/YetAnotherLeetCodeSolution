package easy

import java.util.*

class Sol937 {

    fun reorderLogFiles(logs: Array<String>) =
        logs.partition { it.split(" ")[1][0] in '0'..'9' }
            .let { (digit, letter) ->
                (letter.sortedBy {
                    LinkedList(it.split(" ")).apply { add(removeAt(0)) }
                        .toString()
                } + digit).toTypedArray()
            }
}