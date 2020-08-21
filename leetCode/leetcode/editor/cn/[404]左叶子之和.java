//计算给定二叉树的所有左叶子之和。 
//
// 示例： 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24 
//
// 
// Related Topics 树


//leetcode submit region begin(Prohibit modification and deletion)

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
    int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null) {
            if (isEndPoint(root.left)) {
                result += root.left.val;
            }
            sumOfLeftLeaves(root.left);
        }
        if (root.right != null) {
            sumOfLeftLeaves(root.right);
        }
        return result;
    }

    boolean isEndPoint(TreeNode root) {
        return root != null && root.left == null && root.right == null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
