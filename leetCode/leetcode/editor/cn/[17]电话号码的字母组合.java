//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    LinkedList<String> result;
    String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        result = new LinkedList();
        if(digits.length()==0) return result;
        dfs(digits, 0, new LinkedList<Character>());
        return result;
    }


    void dfs(String digits, int index, LinkedList<Character> list) {
        if (list.size() == digits.length()) {
            result.add(buildString(list));
            return;
        }
        int numberIndex = digits.charAt(index) - '0';
        for (int i = 0; i < map[numberIndex].length(); i++) {
            list.add(map[numberIndex].charAt(i));
            dfs(digits, index + 1, list);
            list.pollLast();
        }
    }

    String buildString(LinkedList<Character> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : list) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
