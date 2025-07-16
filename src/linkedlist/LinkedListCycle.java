package linkedlist;

/**
 * 环形链表
 */
public class LinkedListCycle {
    /**
     * 141. 判断是否有环形链表
     * https://leetcode-cn.com/problems/linked-list-cycle/
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 后置判断，相当于do-while，否则在链表头就匹配了
            if (slow == fast)
                return true;
        }
        return false;
    }

    /**
     * 142. 检测环形链表的入口位置
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            /*
             * 快指针比慢指针多走了一倍的路程
             * 设x为环入口离起点的位置，a为相遇点离环入口的位置
             * x + a = nr = (n - 1)r + r
             * x + a = (n - 1)r + L - x
             * x = (n - 1)r + (L - x - a)
             * 从起点到环入口 = (n - 1)圈 + 相遇点到起点的位置
             */
            if (slow == fast) {
                ListNode slow2 = head;
                while (slow2 != slow) {
                    slow2 = slow2.next;
                    slow = slow.next;
                }
                return slow2;
            }
        }
        return null;
    }
}
