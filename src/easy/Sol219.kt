package easy

class Sol219 {
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        // <num, index last seen>
        val map = HashMap<Int, Int>()
        for ((i, n) in nums.withIndex()) {
            if (map.containsKey(n) && i - map[n]!! <= k) {
                return true
            } else {
                map[n] = i
            }
        }
        return false
    }
}