🙋 我来咯～

❤️ 大佬们随手关注波公众号【[甜姨的奇妙冒险](http://q8bj89g2o.bkt.clouddn.com/sweetiee.jpeg)】和知乎专栏【[甜姨的力扣题解](https://zhuanlan.zhihu.com/c_1224355183452614656)】呀～
❤️ 关注公众号扫码加入 「甜姨的技术交流群」喔！

---

## 题目 

验证二叉搜索树


## 思路

中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足 BST，继续遍历；否则直接返回 false。

## 代码

``` Java
class Solution {
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }
}
```

---
❤️ 求赏个 「爱心赞」吧 o> <o
❤️ 大佬们随手关注波公众号【[甜姨的奇妙冒险](http://q8bj89g2o.bkt.clouddn.com/sweetiee.jpeg)】和知乎专栏【[甜姨的力扣题解](https://zhuanlan.zhihu.com/c_1224355183452614656)】呀～
❤️ 关注公众号扫码加入 「甜姨的技术交流群」喔！