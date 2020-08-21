//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。 
//
// 示例: 
//
// 输入: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4 
// Related Topics 动态规划 
// 👍 490 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //f(i,j) = k(i,j) == 1 ? min(f(i-1,j),f(i-1,j-1),f(i,j-1)) +1:0
    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix.length];
        int len = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                    }
                }
                len = Math.max(len, dp[i][j]);
            }
        }
        return len * len;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
