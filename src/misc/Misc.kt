import kotlin.math.max

// O(nk)
fun josephSim(n: Int, k: Int): Int {
    class Node(val i: Int, var nex: Node? = null)

    val head = Node(1)
    var pre = head
    for (i in 2..n) {
        val new = Node(i)
        pre.nex = new
        pre = new
    }
    pre.nex = head

    var cur = head
    while (cur.nex != cur) {
        var tmp = cur
        for (i in 1..k - 2) tmp = tmp.nex!!
        tmp.nex = tmp.nex!!.nex
        cur = tmp.nex!!
    }
    return cur.i
}

// O(n)
fun josephRec(n: Int, k: Int): Int =
    if (n == 1) 1 else (josephRec(n - 1, k) + k - 1) % n + 1

fun path(src: List<List<String>>, name1: String, name2: String): List<String> {
    // <person1, <person2, [person1 src to person2]>>
    val G = HashMap<String, HashMap<String, ArrayList<String>>>()
    src.forEach { (p1, r, p2) ->
        if (p1 !in G) G[p1] = HashMap()
        if (p2 !in G[p1]!!) G[p1]!![p2] = ArrayList()
        G[p1]!![p2]!!.add("$p1 $r $p2")
    }
    if (name1 !in G) return emptyList()

    val seen = HashSet<String>()
    fun build(p1: String) {
        if (p1 !in G || p1 in seen) return
        seen.add(p1)
        val cur = G[p1]!!
        cur.keys.filterNot { it == p1 }.forEach { build(it) }
        cur.filterNot { it.key == p1 }.forEach { (p2, p1p2s) ->
            if (p2 !in G) return@forEach
            G[p2]!!.filter { it.key == name2 }.forEach { (p3, p2p3s) ->
                if (p3 !in cur) cur[p3] = ArrayList()
                p2p3s.forEach { p2p3 ->
                    val concat = p2p3.substring(p2p3.indexOf(' '))
                    cur[p3]!!.addAll(p1p2s.map { "$it$concat" })
                }
            }
        }
    }
    build(name1)
    return if (name2 in G[name1]!!) G[name1]!![name2]!! else emptyList()
}

fun earliestBooking(books: List<IntArray>, len: Int, date: Int): Int {
    val incoming = books.map {
        it.apply { if (it[1] < date) it[1] = date }
    }.sortedBy { it[0] }
    if (incoming.isEmpty()) return date
    var last = incoming.first()
    incoming.drop(1).forEach {
        if (it[0] - last[1] > len) return last[1]
        if (it[0] > last[1]) last = it
        else last[1] = max(last[1], it[1])
    }
    return last[1]
}
