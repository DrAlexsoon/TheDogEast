//给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。 
//
// 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。 
//
// 回文串不一定是字典当中的单词。 
//
// 
//
// 示例1： 
//
// 输入："tactcoa"
//输出：true（排列有"tacocat"、"atcocta"，等等）
// 
//
// 
// Related Topics 哈希表 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPermutePalindrome(String s) {
        //f(i,j) = f(i+1,j-1) && k(i) == k(j)
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (hashMap.containsKey(chars[i])) {
                hashMap.put(chars[i], hashMap.get(chars[i]) + 1);
            } else {
                hashMap.put(chars[i], 1);
            }
        }
        int count = 0;
        for (Map.Entry<Character, Integer> entry :
                hashMap.entrySet()) {
            if(entry.getValue() %2 ==1){
                count ++;
                if(count >1){
                    return false;
                }
            }

        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
