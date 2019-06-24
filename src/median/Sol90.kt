package median

class Sol90 {

    fun subsetsWithDup(nums: IntArray) = f(nums.sorted(), 0)

    fun f(nums: List<Int>, i: Int): List<List<Int>> =
        if (i == nums.size - 1) {
            listOf(listOf(nums[i]), emptyList())
        } else {
            if (nums[i] == nums[i + 1]) {
                val nex = (i + 1 until nums.size)
                    .firstOrNull { nums[it] != nums[i] }
                if (nex == null) {
                    ArrayList(f(nums, i + 1)).apply {
                        add(IntArray(nums.size - i) { nums[i] }.toList())
                    }
                } else {
                    f(nums, i + 1) + f(nums, nex)
                        .map { it + IntArray(nex - i) { nums[i] }.toList() }
                }
            } else {
                val res = f(nums, i + 1)
                res + res.map { it + nums[i] }
            }
        }
}