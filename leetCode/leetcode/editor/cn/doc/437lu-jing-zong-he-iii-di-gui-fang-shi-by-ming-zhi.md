### 解题思路
#### 题解

本题需要去计算路径和等于给定数值的路径总数，我们依旧遵循树模型的解题思路，按照递归的方式去求解（**递归的一个重要思想就是两部分：1.找到最简单的子问题求解，2.其他问题不考虑内在细节，只考虑整体逻辑**），那我们现在来设计递归关系：

首先，最简单的子问题是什么呢？由于这道题是在树的框架下，我们最容易想到的就是遍历的终止条件：

```java
if(root == null){
    return 0;
}
```

接下来，我们来考虑再上升的一个层次，题目要求  **路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）**  。这就要求我们只需要去求三部分即可：

- 以当前节点作为头结点的路径数量
- 以当前节点的左孩子作为头结点的路径数量
- 以当前节点的右孩子作为头结点啊路径数量

将这三部分之和作为最后结果即可。

最后的问题是：我们应该如何去求以当前节点作为头结点的路径的数量？这里依旧是按照树的遍历方式模板，每到一个节点让sum-root.val，并判断sum是否为0，如果为零的话，则找到满足条件的一条路径。

### 代码

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        int result = countPath(root,sum);
        int a = pathSum(root.left,sum);
        int b = pathSum(root.right,sum);
        return result+a+b;

    }
    public int countPath(TreeNode root,int sum){
        if(root == null){
            return 0;
        }
        sum = sum - root.val;
        int result = sum == 0 ? 1:0;
        return result + countPath(root.left,sum) + countPath(root.right,sum);
    }
}
```