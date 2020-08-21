//给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值
// 至多为 k。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,2,3,1], k = 3
//输出: true 
//
// 示例 2: 
//
// 输入: nums = [1,0,1,1], k = 1
//输出: true 
//
// 示例 3: 
//
// 输入: nums = [1,2,3,1,2,3], k = 2
//输出: false 
// Related Topics 数组 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (dict.containsKey(nums[i])) {
                int pre = dict.get(nums[i]);
                if (i - pre <= k) {
                    return true;
                }
            }
            dict.put(nums[i], i);
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
