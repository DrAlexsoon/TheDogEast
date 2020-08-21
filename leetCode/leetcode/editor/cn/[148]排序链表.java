//在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。 
//
// 示例 1: 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2: 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5 
// Related Topics 排序 链表


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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = new ListNode(-1);
        pre.next = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            pre = pre.next;
        }
        pre.next = null;

        return merge(sortList(head), sortList(slow));
    }

    ListNode merge(ListNode left, ListNode right) {
        ListNode result = new ListNode(-1);
        ListNode current = result;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        current.next = left == null ? right : left;
        return result.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
