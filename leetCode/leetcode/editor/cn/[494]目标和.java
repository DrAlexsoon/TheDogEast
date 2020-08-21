//给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选
//择一个符号添加在前面。 
//
// 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。 
//
// 
//
// 示例： 
//
// 输入：nums: [1, 1, 1, 1, 1], S: 3
//输出：5
//解释：
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//
//一共有5种方法让最终目标和为3。
// 
//
// 
//
// 提示： 
//
// 
// 数组非空，且长度不会超过 20 。 
// 初始的数组的和不会超过 1000 。 
// 保证返回的最终结果能被 32 位整数存下。 
// 
// Related Topics 深度优先搜索 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //f(i,j) = f(i-1,j-k(i)) || dp(i-1,j+k(i))
    public int findTargetSumWays(int[] nums, int S) {
        int length = 0;
        for (int i = 0; i < nums.length; i++) {
            length += nums[i];
        }
        if (Math.abs(S) > length) return 0;

        int[][] dp = new int[nums.length][2 * length + 1];
        dp[0][length + nums[0]] = 1;
        dp[0][length - nums[0]] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= 2 * length; j++) {
                int l = j - nums[i] >= 0 && dp[i - 1][j - nums[i]] > 0 ? dp[i - 1][j - nums[i]] : 0;
                int r = j + nums[i] <= 2 * length && dp[i - 1][j + nums[i]] > 0 ? dp[i - 1][j + nums[i]] : 0;
                dp[i][j] = l + r;
            }
        }
        return dp[nums.length - 1][length + S];
    }

}
//leetcode submit region end(Prohibit modification and deletion)
