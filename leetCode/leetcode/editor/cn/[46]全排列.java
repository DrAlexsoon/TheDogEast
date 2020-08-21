//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * [1,2,3]
 */
class Solution {
    LinkedList<List<Integer>> result;

    public List<List<Integer>> permute(int[] nums) {
        result = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        for (int num : nums) {
            list.add(num);
        }
        permute(list, 0);
        return result;
    }

    void permute(LinkedList<Integer> list, int index) {
        if (index == list.size()) {
            result.add(new ArrayList<>(list));
        }
        for (int i = index; i < list.size(); i++) {
            Collections.swap(list, i, index);
            permute(list, i + 1);
            Collections.swap(list, i, index);
        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)
