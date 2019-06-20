package easy

class Sol349 {
    fun intersection(nums1: IntArray, nums2: IntArray) =
        nums1.toSet().intersect(nums2.toSet()).toIntArray()
}
