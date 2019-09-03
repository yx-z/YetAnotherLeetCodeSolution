package median

class Sol390 {

    fun lastRemaining(n: Int): Int {
        var left = true
        var rem = n
        var step = 1
        var head = 1
        while (rem > 1) {
            if (left || rem % 2 == 1) head += step
            rem /= 2
            step *= 2
            left = !left
        }
        return head
    }
}