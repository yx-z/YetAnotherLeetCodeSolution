package easy

class Sol167 {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var lo = 0
        var hi = numbers.size - 1
        while (lo < hi) {
            val s = numbers[lo] + numbers[hi]
            if (s == target) {
                return intArrayOf(lo + 1, hi + 1)
            } else if (s > target) {
                hi--
            } else { // s < target
                lo++
            }
        }
        return IntArray(0)
    }
}