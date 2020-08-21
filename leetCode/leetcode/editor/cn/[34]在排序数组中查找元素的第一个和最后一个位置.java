//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 如果数组中不存在目标值，返回 [-1, -1]。 
//
// 示例 1: 
//
// 输入: nums = [5,7,7,8,8,10], target = 8
//输出: [3,4] 
//
// 示例 2: 
//
// 输入: nums = [5,7,7,8,8,10], target = 6
//输出: [-1,-1] 
// Related Topics 数组 二分查找


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] == target) {
                int start = mid;
                int end = mid;
                while (start >= 0 && nums[start] == target) {
                    start--;
                }
                while (end < nums.length && nums[end] == target) {
                    end++;
                }
                return new int[]{start + 1, end - 1};
            } else if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }

        }
        return new int[]{-1, -1};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
