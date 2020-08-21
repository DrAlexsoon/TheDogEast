//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[i] + nums[j] + nums[left] + nums[right] == target) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[left]);
                        tmp.add(nums[right]);
                        set.add(tmp);
                        left++;
                        right--;
                    } else if (nums[i] + nums[j] + nums[left] + nums[right] > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return new LinkedList(set);
    }


}
//leetcode submit region end(Prohibit modification and deletion)
