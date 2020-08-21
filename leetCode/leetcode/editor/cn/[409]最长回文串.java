//给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。 
//
// 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。 
//
// 注意: 
//假设字符串的长度不会超过 1010。 
//
// 示例 1: 
//
// 
//输入:
//"abccccdd"
//
//输出:
//7
//
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
// 
// Related Topics 哈希表


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindrome(String s) {

        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            dict.put(c, dict.getOrDefault(c, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Character, Integer> entry : dict.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                count += entry.getValue();
            } else {
                if (entry.getValue() != 1) {
                    count += entry.getValue() - 1;
                }
            }
        }
        if (count < s.length()) {
            return count + 1;
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
