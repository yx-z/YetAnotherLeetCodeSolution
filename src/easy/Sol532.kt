package easy

class Sol532 {

    fun findPairs(nums: IntArray, k: Int): Int {
        if (k < 0) return 0
        val seen = HashSet<Int>()
        val valid = HashSet<Int>()
        for (n in nums) {
            if (n + k in seen) valid.add(n)
            if (n - k in seen) valid.add(n - k)
            seen.add(n)
        }
        return valid.size
    }
}