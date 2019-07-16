package linkedlist;

public class PalindromeLinkedList {
    /**
     * 234. 回文链表
     * https://leetcode-cn.com/problems/palindrome-linked-list/
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;
        ListNode middle = findMiddle(head);
        middle.next = reverse(middle.next); //反转链表后重新拼接
        ListNode p1 = head;
        ListNode p2 = middle.next;
        while (p2 != null && p1.val == p2.val) { //往后遍历并比较
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2 == null;
    }

    /**
     * 使用快慢指针找到链表中点
     *
     * @param head
     * @return
     */
    private ListNode findMiddle(ListNode head) {
        ListNode fast = head.next, slow = head; //位置参考sort.SortList所述
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode tail;
        while (head != null) {
            tail = head.next;
            head.next = prev;
            prev = head;
            head = tail;
        }
        return prev;
    }
}
