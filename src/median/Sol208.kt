package median

class Sol208 {

    /**
     * Your Trie object will be instantiated and called as such:
     * var obj = Trie()
     * obj.insert(word)
     * var param_2 = obj.search(word)
     * var param_3 = obj.startsWith(prefix)
     */
    class Trie {

        private class Node(val char: Char) {
            val next = HashSet<Node>()
        }

        /** Initialize your data structure here. */
        private val root = Node(0.toChar())

        /** Inserts a word into the trie. */
        fun insert(word: String) {
            var curr = root
            word.toCharArray().forEach { c ->
                curr = curr.next.firstOrNull { it.char == c }
                    ?: Node(c).apply { curr.next.add(this) }
            }
            curr.next.add(root) // mark as end of the inserted word
        }

        /** Returns if the word is in the trie. */
        fun search(word: String): Boolean {
            var curr: Node? = root
            return word.toCharArray().all { c ->
                curr = curr!!.next.firstOrNull { it.char == c }
                curr != null
            } && curr!!.next.contains(root)
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        fun startsWith(prefix: String): Boolean {
            var curr: Node? = root
            return prefix.toCharArray().all { c ->
                curr = curr!!.next.firstOrNull { it.char == c }
                curr != null
            }
        }
    }
}
