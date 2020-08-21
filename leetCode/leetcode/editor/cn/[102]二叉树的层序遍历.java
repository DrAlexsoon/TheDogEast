//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索


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
    List<List<Integer>> result;

    public List<List<Integer>> levelOrder(TreeNode root) {
        result = new LinkedList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        if (root != null) {
            linkedList.add(root);
        }
        dfs(linkedList);
        return result;
    }

    void dfs(LinkedList<TreeNode> linkedList) {
        int size = linkedList.size();
        if (size == 0) {
            return;
        }
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            TreeNode node = linkedList.pollFirst();
            if (node.left != null) {
                linkedList.addLast(node.left);
            }
            if (node.right != null) {
                linkedList.addLast(node.right);
            }
            arrayList.add(node.val);
        }
        result.add(arrayList);
        dfs(linkedList);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
