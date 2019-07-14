package sort;

import linkedlist.ListNode;

/**
 * 排序链表问题
 */
public class SortList {
    /**
     * 147. 对链表进行插入排序
     * https://leetcode-cn.com/problems/insertion-sort-list/
     *
     * @param head
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        //不能给dummy赋值后继结点，将它当成是一个新的链表
        //dummy.next = head;
        for (ListNode cur = head; cur != null; ) {
            ListNode pos = findInsertPos(dummy, cur.val); //找到前方排好序部分的插入位置
            //插入到pos之后
            ListNode tmp = cur.next;
            cur.next = pos.next;
            pos.next = cur;
            cur = tmp;
        }
        return dummy.next;
    }

    /**
     * 找到插入位置，插入位置与其之前都是小于等于val的结点
     *
     * @param head
     * @param val
     * @return
     */
    private static ListNode findInsertPos(ListNode head, int val) {
        ListNode pre = head;
        for (ListNode cur = pre.next; cur != null && cur.val <= val; ) {
            pre = cur;
            cur = cur.next;
        }
        return pre;
    }

    /**
     * 21. 合并两个有序链表
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        //使用新链表存储归并结果
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for (; l1 != null && l2 != null; p = p.next) {
            if (l1.val > l2.val) {
                p.next = l2;
                l2 = l2.next;
            } else {
                p.next = l1;
                l1 = l1.next;
            }
        }
        //处理剩余结点
        p.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

    /**
     * 148. 排序链表
     * https://leetcode-cn.com/problems/sort-list/
     * 常数空间且 O(nlogn) ，单链表适合用归并排序，双向链表适合用快速排序
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        // 空或单结点时返回
        if (head == null || head.next == null)
            return head;
        //使用快慢指针找到中间结点
        //有两种情况
        //1.慢指针当结点数为奇数时指向中间结点，当结点数为偶数时指向中间偏后的那个结点
        //ListNode slow = head, fast = head;
        //2.慢指针当结点数为奇数时指向中间结点，当结点数为偶数时指向中间偏前的那个结点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //从中间结点拆开两个链表
        ListNode head2 = slow.next;
        slow.next = null;
        //递归排序两个链表
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(head2);
        //合并两个链表
        return mergeTwoLists(l1, l2);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        System.out.println(sortList(head));
    }
}
