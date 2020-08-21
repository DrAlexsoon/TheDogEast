//给定一个二维网格和一个单词，找出该单词是否存在于网格中。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//DFS
class Solution {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(i, j, 0, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(int i, int j, int index, char[][] board, String word) {
        if (index == word.length()) {
            return true;
        }
        if (i >= 0 && i <= board.length - 1 && j >= 0 && j <= board[0].length - 1 && board[i][j] == word.charAt(index)) {
            char c = board[i][j];
            board[i][j] = '\\';
            boolean b = dfs(i + 1, j, index + 1, board, word) ||
                    dfs(i - 1, j, index + 1, board, word) ||
                    dfs(i, j + 1, index + 1, board, word) ||
                    dfs(i, j - 1, index + 1, board, word);
            board[i][j] = c;
            return b;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
