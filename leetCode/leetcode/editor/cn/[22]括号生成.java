//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> result;

    public List<String> generateParenthesis(int n) {
        result = new LinkedList<>();
        back(n, n, new StringBuilder());
        return result;
    }

    void back(int left, int right, StringBuilder stringBuilder) {
        if (left == 0 && right == 0) {
            result.add(stringBuilder.toString());
        }
        if (left != 0) {
            stringBuilder.append('(');
            back(left - 1, right, stringBuilder);
            stringBuilder.setLength(stringBuilder.length() - 1);

        }
        if (right > left) {
            stringBuilder.append(')');
            back(left, right - 1, stringBuilder);
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
