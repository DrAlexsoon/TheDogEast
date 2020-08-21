//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。 
//
// 注意： 
//
// 
// num1 和num2 的长度都小于 5100. 
// num1 和num2 都只包含数字 0-9. 
// num1 和num2 都不包含任何前导零。 
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addStrings(String num1, String num2) {
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        StringBuilder result = new StringBuilder();
        int tmp = 0;
        while (l1 >= 0 || l2 >= 0) {
            int i = l1 < 0 ? 0 : Integer.valueOf(num1.charAt(l1)) - '0';
            int j = l2 < 0 ? 0 : Integer.valueOf(num2.charAt(l2)) - '0';
            int total = i + j + tmp;
            result.append(Integer.valueOf(total % 10));
            tmp = total / 10;
            l1--;
            l2--;
        }
        if (tmp != 0) {
            result.append(tmp);
        }
        return result.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
