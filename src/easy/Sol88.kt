package easy

class Sol88 {
    /*
    Input:
    nums1 = [1,2,3,0,0,0], m = 3
    nums2 = [2,5,6],       n = 3

    Output: [1,2,2,3,5,6]
     */
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var i1 = m - 1
        var i2 = n - 1
        for (i in (m + n - 1) downTo 0) {
            when {
                i1 < 0 -> {
                    nums1[i] = nums2[i2]
                    i2--
                }
                i2 < 0 -> {
                    nums1[i] = nums1[i1]
                    i1--
                }
                nums1[i1] >= nums2[i2] -> {
                    nums1[i] = nums1[i1]
                    i1--
                }
                else -> {
                    nums1[i] = nums2[i2]
                    i2--
                }
            }
        }
    }
}
