package hard

class Sol45 {

    fun jump(nums: IntArray): Int {
        var count = 0
        var end = 0
        var farthest = 0
        for (i in 0..nums.size - 2) {
            farthest = Math.max(farthest, i + nums[i])
            if (i == end) {
                count++
                end = farthest
            }
        }
        return count
    }
}