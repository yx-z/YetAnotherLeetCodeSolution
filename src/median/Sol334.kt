package median

class Sol334 {

    fun increasingTriplet(nums: IntArray): Boolean {
        if (nums.size < 3) {
            return false
        }
        var smallest = Int.MAX_VALUE
        var secondSmallest = Int.MAX_VALUE
        for (n in nums) {
            when {
                n <= smallest -> smallest = n
                n <= secondSmallest -> secondSmallest = n
                else -> return true
            }
        }
        return false
    }
}