//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。 
//
// 示例 1: 
//
// 输入: "hello"
//输出: "holle"
// 
//
// 示例 2: 
//
// 输入: "leetcode"
//输出: "leotcede" 
//
// 说明: 
//元音字母不包含字母"y"。 
// Related Topics 双指针 字符串


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseVowels(String s) {
        HashSet<Character> hashSet = new HashSet<>();
        boolean[] ints = new boolean[126];
        ints['a'] = true;
        ints['A'] = true;
        ints['e'] = true;
        ints['E'] = true;
        ints['i'] = true;
        ints['I'] = true;
        ints['o'] = true;
        ints['O'] = true;
        ints['u'] = true;
        ints['U'] = true;
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            while (left <= s.length() - 1 && !ints[chars[left]]) {
                left++;
            }
            while (right >= 0 && !ints[chars[right]]) {
                right--;
            }

            if (left < right) {
                swap(chars, left, right);
                left++;
                right--;
            } else {
                break;
            }
        }
        return new String(chars);
    }

    void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
