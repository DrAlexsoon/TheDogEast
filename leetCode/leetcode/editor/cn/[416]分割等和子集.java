//给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 注意: 
//
// 
// 每个数组中的元素不会超过 100 
// 数组的大小不会超过 200 
// 
//
// 示例 1: 
//
// 输入: [1, 5, 11, 5]
//
//输出: true
//
//解释: 数组可以分割成 [1, 5, 5] 和 [11].
// 
//
// 
//
// 示例 2: 
//
// 输入: [1, 2, 3, 5]
//
//输出: false
//
//解释: 数组不能分割成两个元素和相等的子集.
// 
//
// 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += num;
        }
        if (count % 2 == 1) {
            return false;
        }
        count = count / 2;
        boolean[][] dp = new boolean[nums.length][count + 1];
        dp[0][0] = true;
        if (nums[0] == count) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= count; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][count];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
