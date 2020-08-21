//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ia = a.length() - 1;
        int ib = b.length() - 1;
        int tmp = 0;
        while (ia >= 0 || ib >= 0) {
            int na = ia < 0 ? 0 : a.charAt(ia) - '0';
            int nb = ib < 0 ? 0 : b.charAt(ib) - '0';
            int total = na + nb + tmp;
            sb.append(total % 2);
            tmp = total / 2;
            ia--;
            ib--;
        }
        while (tmp != 0) {
            sb.append(tmp % 2);
            tmp /= 2;
        }

        return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
