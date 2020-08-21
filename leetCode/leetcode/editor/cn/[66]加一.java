//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1]++;
        int tmp = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int total = digits[i] + tmp;
            digits[i] = total % 10;
            tmp = total / 10;
        }
        if(tmp != 0){
            int[] ints = new int[digits.length + 1];
            ints[0] = tmp;
            for (int i = 0; i < digits.length; i++) {
                ints[i+1] = digits[i];
            }
            return ints;
        }
        return digits;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
