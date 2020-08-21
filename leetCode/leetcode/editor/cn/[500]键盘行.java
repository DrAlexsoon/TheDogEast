//给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。 
//
// 
//
// 
//
// 
//
// 示例： 
//
// 输入: ["Hello", "Alaska", "Dad", "Peace"]
//输出: ["Alaska", "Dad"]
// 
//
// 
//
// 注意： 
//
// 
// 你可以重复使用键盘上同一字符。 
// 你可以假设输入的字符串将只包含字母。 
// Related Topics 哈希表 
// 👍 99 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String[] findWords(String[] words) {
        char[] a = new char[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
        char[] b = new char[]{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'};
        char[] c = new char[]{'z', 'x', 'c', 'v', 'b', 'n', 'm'};
        HashMap<Character, Integer> hashMap = new HashMap();
        for (char c1 : a) {
            hashMap.put(c1, 1);
        }
        for (char c1 : b) {
            hashMap.put(c1, 2);
        }
        for (char c1 : c) {
            hashMap.put(c1, 3);
        }
        LinkedList<String> linkedList = new LinkedList<>();

        for (String word : words) {
            int col = hashMap.get(Character.toLowerCase(word.charAt(0)));
            int i = 1;
            for (; i < word.length(); i++) {
                if (col != hashMap.get(Character.toLowerCase(word.charAt(i)))) {
                    break ;
                }
            }
            if(i == word.length()){
                linkedList.add(word);
            }

        }
        String[] strings = new String[linkedList.size()];
        return linkedList.toArray(strings);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
