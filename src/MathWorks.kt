fun main() {
    println(generateSequence(-5) { it - 3 }.take(101).last())
}