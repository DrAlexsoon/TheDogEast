//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


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
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7]
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return reBuild(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    TreeNode reBuild(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        if (p_start == p_end) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[p_start]);
        int pos = findPos(inorder, preorder[p_start], i_start, i_end);
        int left_len = pos - i_start;
        TreeNode left = reBuild(inorder, p_start + 1, p_start + left_len + 1, inorder, i_start, left_len);
        TreeNode right = reBuild(inorder, p_start + left_len + 1, p_end, inorder, pos + 1, i_end);
        node.left = left;
        node.right = right;
        return node;

    }

    int findPos(int[] inorder, int dest, int start, int end) {
        while (start < end) {
            if (inorder[start] == dest) {
                return start;
            }
            start++;
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
