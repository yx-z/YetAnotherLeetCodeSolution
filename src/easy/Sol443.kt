package easy

class Sol443 {

    fun compress(chars: CharArray): Int {
        var write = 0
        var read = 0
        while (read < chars.size) {
            val curr = chars[read]
            var count = 0
            while (read < chars.size && chars[read] == curr) {
                count++
                read++
            }
            chars[write] = curr
            write++
            if (count > 1) {
                for (s in count.toString()) {
                    chars[write] = s
                    write++
                }
            }
        }
        return write
    }
}