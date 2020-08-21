//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//] 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new LinkedList<>();

        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> tmp = new ArrayList<>(i + 1);
            tmp.add(1);
            if(i !=0){
                for (int j = 1; j < i ; j++) {
                    tmp.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
                tmp.add(1);
            }
            result.add(tmp);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
