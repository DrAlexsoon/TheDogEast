//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
//


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int movingCount(int m, int n, int k) {
        boolean[][] route = new boolean[m][n];

        return dfs(0, 0, route, k);
    }

    int dfs(int i, int j, boolean[][] route, int k) {
        if (i < 0 || i >= route.length || j < 0 || j >= route[0].length || route[i][j] || parse(i, j, k)) {
            return 0;
        }
        route[i][j] = true;

        return 1 + dfs(i + 1, j, route, k)
                + dfs(i - 1, j, route, k)
                + dfs(i, j + 1, route, k)
                + dfs(i, j - 1, route, k);
    }

    boolean parse(int i, int j, int k) {
        int x = i;
        int y = j;
        int count = 0;
        while (x != 0) {
            count += x % 10;
            x = x / 10;
        }
        while (y != 0) {
            count += y % 10;
            y = y / 10;
        }
        return count > k;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
