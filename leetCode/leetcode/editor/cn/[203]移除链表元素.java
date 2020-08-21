//删除链表中等于给定值 val 的所有节点。 
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
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
    public ListNode removeElements(ListNode head, int val) {
        ListNode result = new ListNode(-1);
        result.next = head;
        ListNode pre = result;

        while (head != null) {
            if (head.val == val) {
                head = head.next;
                pre.next = head;

            } else {
                pre = head;
                head = head.next;
            }
        }
        return result.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
