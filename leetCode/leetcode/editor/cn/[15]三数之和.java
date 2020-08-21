//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if(nums[i] > 0){
                break;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];
                if (total == 0) {
                    ArrayList<Integer> tmp = new ArrayList<>(3);
                    tmp.add(nums[i]);
                    tmp.add(nums[left]);
                    tmp.add(nums[right]);
                    set.add(tmp);
                    left++;
                    right--;
                } else if (total > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return new LinkedList<>(set);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
