//将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。 
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定有序数组: [-10,-3,0,5,9],
//
//一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 树 深度优先搜索


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
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    TreeNode sortedArrayToBST(int[] nums, int i, int j) {
        if (i >= nums.length || i < 0 || i > j) {
            return null;
        } else if (i == j) {
            return new TreeNode(nums[i]);
        } else {
            int mid = (i + j) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = sortedArrayToBST(nums, i, mid - 1);
            node.right = sortedArrayToBST(nums, mid + 1, j);
            return node;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
