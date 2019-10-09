package median

class Sol211 {

    class Node(
        val curr: Char,
        var isEnd: Boolean = false,
        val next: HashMap<Char, Node> = HashMap()
    )


    class WordDictionary {

        /** Initialize your data structure here. */
        val root = Node(0.toChar())


        /** Adds a word into the data structure. */
        fun addWord(word: String) {
            var curr = root
            for (c in word) {
                if (c !in curr.next) {
                    curr.next[c] = Node(c)
                }
                curr = curr.next[c]!!
            }
            curr.isEnd = true
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        fun search(word: String, lo: Int = 0, curr: Node = root): Boolean {
            if (lo == word.length) {
                return curr.isEnd
            }
            val c = word[lo]
            if (c != '.') {
                if (c !in curr.next) {
                    return false
                }
                return search(word, lo + 1, curr.next[c]!!)
            } else {
                return curr.next.values.any { search(word, lo + 1, it) }
            }
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * var obj = WordDictionary()
     * obj.addWord(word)
     * var param_2 = obj.search(word)
     */
}