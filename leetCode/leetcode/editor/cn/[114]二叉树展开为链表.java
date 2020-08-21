//给定一个二叉树，原地将它展开为一个单链表。 
//
// 
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
// Related Topics 树 深度优先搜索 
// 👍 428 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        backStack(root);

    }

    TreeNode backStack(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = backStack(root.left);
        TreeNode right = backStack(root.right);
        if (root.right != null && left != null) {
            TreeNode iter = left;
            while (iter.right != null) {
                iter = iter.right;
            }
            iter.right = right;

        }
        root.left = null;
        if(left != null){
            root.right = left;
        }else{
            root.right = right;
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
