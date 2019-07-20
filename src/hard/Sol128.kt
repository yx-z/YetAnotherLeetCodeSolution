package hard

class Sol128 {

    fun longestConsecutive(nums: IntArray) = nums.toSet().run {
        filter { !contains(it - 1) }.map {
            var cur = it
            while (contains(cur)) cur++
            cur - it
        }.max() ?: 0
    }
}