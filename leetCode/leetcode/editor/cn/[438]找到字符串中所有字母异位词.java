//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。 
//
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。 
//
// 说明： 
//
// 
// 字母异位词指字母相同，但排列不同的字符串。 
// 不考虑答案输出的顺序。 
// 
//
// 示例 1: 
//
// 
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
// 
//
// 示例 2: 
//
// 
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
// 
// Related Topics 哈希表 
// 👍 329 👎 0


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] need = new int[26];
        int[] window = new int[26];
        for (char c : p.toCharArray()) {
            need[c - 'a']++;
        }
        int left = 0;
        int right = 0;
        LinkedList<Integer> list = new LinkedList<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window[c - 'a']++;
            while (window[c - 'a'] > need[c - 'a']) {
                window[s.charAt(left) - 'a'] -= 1;
                left++;
            }
            if(right -left == p.length()){
                list.add(left);
            }
        }
        return list;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
