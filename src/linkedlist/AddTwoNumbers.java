/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package linkedlist;

/**
 * @author linjie
 * @version : AddTwoNumbers.java, v 0.1 2021年08月06日 1:07 上午 linjie Exp $
 */
public class AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }


    /**
     * 445. 两数相加 II
     * https://leetcode.cn/problems/add-two-numbers-ii/description/
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        int pre = 0;
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(l1 != null && l2 != null) {
            cur.next = new ListNode((l1.val + l2.val + pre) % 10);
            cur = cur.next;
            pre = (l1.val + l2.val + pre) / 10;
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null) {
            cur.next = new ListNode((l1.val + pre) % 10);
            pre = (l1.val + pre) / 10;
            cur = cur.next;
            l1 = l1.next;
        }

        while(l2 != null) {
            cur.next = new ListNode((l2.val + pre) % 10);
            pre = (l2.val + pre) / 10;
            cur = cur.next;
            l2 = l2.next;
        }

        if (pre != 0) {
            cur.next = new ListNode(pre);
        }
        return reverse(head.next);
    }

}