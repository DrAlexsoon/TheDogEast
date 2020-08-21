//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<String> result;

    public List<String> binaryTreePaths(TreeNode root) {
        result = new LinkedList();
        backstack(root, "");
        return result;
    }

    void backstack(TreeNode root, StringBuilder path) {
        if (root != null) {
            path += Integer.toString(root.val);
            if ((root.left == null) && (root.right == null))
                result.add(path);
            else {
                path += "->";
                backstack(root.left, path);
                backstack(root.right, path);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
