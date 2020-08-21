//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    ListNode fornNode;

    public boolean isPalindrome(ListNode head) {
        fornNode = head;
        return check(head);
    }

    boolean check(ListNode head) {
        if (head != null) {
            if (!check(head.next)) {
                return false;
            }
            if (head.val != fornNode.val) {
                return false;
            }
            fornNode = fornNode.next;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
