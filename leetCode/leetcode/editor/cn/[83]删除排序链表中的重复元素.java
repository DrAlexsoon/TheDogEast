//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表


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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = new ListNode(-1);
        result.next = head;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null) {
            if (fast.val == slow.val) {
                fast = fast.next;
            } else {
                slow.next = fast;
                slow = slow.next;
                fast = fast.next;
            }
        }
        if (slow.next != null) {
            slow.next = null;
        }
        return result.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
