//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。 
//
// 示例 1: 
//
// 输入: n = 12
//输出: 3 
//解释: 12 = 4 + 4 + 4. 
//
// 示例 2: 
//
// 输入: n = 13
//输出: 2
//解释: 13 = 4 + 9. 
// Related Topics 广度优先搜索 数学 动态规划 
// 👍 516 👎 0


import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //f(n) = f(n- k(i)) +1
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        ArrayList<Integer> integers = tmpBuilder(n);
        for (int i = 2; i <= n; i++) {
            for (int j = integers.size() - 1; j > 0; j--) {
                if (i - integers.get(j) >= 0 && dp[i - integers.get(j)] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - integers.get(j)] + 1);
                }
            }
        }
        return dp[n];
    }
    ArrayList<Integer> tmpBuilder(int n) {
        int i = 1;
        while (i * i < n) {
            i++;
        }

        ArrayList<Integer> integers = new ArrayList<>(i);
        for (int j = 0; j <= i; j++) {
            integers.add(j, j * j);
        }
        return integers;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
