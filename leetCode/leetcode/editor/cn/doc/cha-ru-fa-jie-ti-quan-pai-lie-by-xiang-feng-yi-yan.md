### 解题思路
所谓“插入法”，其实就是按个插入。本着大问题划分为小问题的指导思想。从1个待排列数字开始思考，逐渐增加待排列数字的个数，过程如下图所示：

 [image.png](https://pic.leetcode-cn.com/726861aaec45501d2d477f90bf13bb86d300640b642d01985d2b8bae5b8f4302-image.png)

1. 待排列数字为 [1, 2, 3]，先排列数字 1 ，结果是 [1]。
2. 再排列数字 2 ，插入到上一个排列中，得到[2,1]和[1,2]。
3. 再排列数字 3 ，现在的集合中包含两个排列了，于是遍历，插入到第一个排列[2,1]中，从前往后插入，得到[3,2,1], [2,3,1], [2,1,3]。继续遍历插入下一个排列。

插入的时候原排列顺序不要变，插入法不是移动法。

之所为叫插入，是我随便起的。若有雷同，纯属巧合。

### 代码

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ArrayList<List<Integer>> lists = new ArrayList<>();
        ArrayList<List<Integer>> tempLists = new ArrayList<>();
        // 从前向后逐个插入到之前的排列中
        for (int i = 0; i < nums.length; i++) {
            // 待插入数字
            int num = nums[i];
            // 无处可插
            if (i == 0) {
                lists.add(Arrays.asList(num));
                continue;
            }
            // 遍历每一条数字排列
            int listSize = lists.size();
            for (int j = 0; j < listSize; j++) {
                // 插它
                List<Integer> list = lists.get(j);
                for (int k = 0; k <= list.size(); k++) {
                    // 插完后新的排列放到缓存里
                    ArrayList<Integer> tempList = new ArrayList<>(list);
                    tempList.add(k, num);
                    tempLists.add(tempList);
                }
                // 排列全部遍历完毕，清空，更新
                if (j + 1 == listSize) {
                    lists.clear();
                    lists.addAll(tempLists);
                    tempLists.clear();
                }
            }
        }
        return lists;
    }
}
```