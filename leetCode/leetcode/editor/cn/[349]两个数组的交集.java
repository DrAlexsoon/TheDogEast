//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4] 
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> dict = new HashSet<Integer>();
        for (int i : nums1) {
            dict.add(i);
        }
        Set<Integer> list = new HashSet<>();
        for (int i : nums2) {
            if(dict.contains(i)){
                list.add(i);
            }
        }
        int[] ints = new int[list.size()];
        int index = 0;
        for (Integer integer : list) {
            ints[index++] = integer;
        }
        return ints;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
