//假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来
//重建这个队列。 
//
// 注意： 
//总人数少于1100人。 
//
// 示例 
//
// 
//输入:
//[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
//输出:
//[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
// 
// Related Topics 贪心算法


import java.util.Arrays;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (r1, r2) -> {
            if (r1[0] != r2[0]) {
                return r2[0] - r1[0];
            } else {
                return r1[1] - r2[1];
            }
        });
        LinkedList<int[]> ints = new LinkedList<>();
        for (int[] person : people) {
            ints.add(person[1],person);
        }
        return ints.toArray(new int[people.length][2]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
