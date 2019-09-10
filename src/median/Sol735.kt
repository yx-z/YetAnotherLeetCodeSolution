package median

import java.util.*

class Sol735 {

    fun asteroidCollision(asteroids: IntArray): IntArray {
        val dq = LinkedList<Int>()
        for (ast in asteroids) {
            var cur: Int? = ast
            while (dq.isNotEmpty() && 0 in cur!! + 1 until dq.peekLast()) {
                if (dq.peekLast() + cur == 0) {
                    dq.removeLast()
                    cur = null
                    break
                }
                if (dq.peekLast() > cur) {
                    cur = null
                    break
                }
                dq.removeLast()
            }
            if (cur != null) dq.addLast(cur)
        }
        return dq.toIntArray()
    }
}