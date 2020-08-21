//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 限制： 
//
// 
// 0 <= matrix.length <= 100 
// 0 <= matrix[i].length <= 100 
// 
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/ 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int index = 0;

    public int[] spiralOrder(int[][] matrix) {
        int[] result = new int[matrix.length * matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {

        }
    }

    void append(int[][] matrix, int[] result, int i, int maxI, int j, int maxJ) {
        for (int k = i; k < maxI; k++) {
            for (int l = j; l < maxJ; l++) {

                result[index++] = matrix[k][];
            }

        }
        while (i < maxI && j < maxJ) {
            if()
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
