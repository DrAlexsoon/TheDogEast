//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //f(i,j) = f(i+1,j-1) && k(i) == k(j)
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLen = 0;
        int startIndex = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    startIndex = i;
                }
            }


        }
        return s.substring(startIndex, startIndex + maxLen);
    }
}


//leetcode submit region end(Prohibit modification and deletion)
