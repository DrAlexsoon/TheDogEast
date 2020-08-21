//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
//[1,2,3]
class Solution {
    List<List<Integer>> result;

    public List<List<Integer>> subsets(int[] nums) {
        result = new LinkedList<>();
        backTrack(0,nums,new LinkedList<Integer>());
        return result;
    }

    void backTrack(int index, int[] nums, LinkedList<Integer> list) {
        if (index == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backTrack(i + 1, nums, list);
            list.pollLast();
        }
        result.add(new LinkedList<>(list));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
