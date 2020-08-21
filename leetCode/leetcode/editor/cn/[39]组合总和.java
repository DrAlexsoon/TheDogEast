//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [2,3,6,7], target = 7,
//所求解集为:
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,3,5], target = 8,
//所求解集为:
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
// Related Topics 数组 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> result;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new LinkedList<>();
        backTrack(candidates, 0, 0, target, new LinkedList<Integer>());
        return result;
    }

    void backTrack(int[] candidates, int index, int current, int target, LinkedList<Integer> list) {
        if (current == target) {
            result.add(new ArrayList<>(list));
            return;
        }
        if(current > target){
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            current += candidates[i];
            list.add(candidates[i]);
            backTrack(candidates, i , current, target, list);
            current -= candidates[i];
            list.pollLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
