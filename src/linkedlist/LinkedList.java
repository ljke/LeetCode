package linkedlist;

/**
 * 链表相关
 */
public class LinkedList {
    /**
     * 反转链表
     * https://leetcode.cn/problems/reverse-linked-list/
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //不需要判断空链表，因为有一个默认空结点
//        if (head == null || head.next == null) {
//            return head;
//        }
        //三个指针指向连续的3个位置
        ListNode p = null;
        ListNode q = head;
        ListNode r;
        while (q != null) { //最后一个位置到达null
            //保存后方位置
            r = q.next;
            //反转指向
            q.next = p;
            //移动指针
            p = q;
            q = r;
        }
        return p;
    }

    /**
     * 反转链表-递归版
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head.next;
        head.next = null; //断开指向
        ListNode newHead = reverseList2(tail); //newHead保存最后一个结点
        tail.next = head; //重新指向
        return newHead;
    }
    /**
     * 区间反转
     * https://leetcode.cn/problems/reverse-linked-list-ii/
     *
     * @param head
     * @param m    区间起始位置
     * @param n    区间结束位置
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy; //注意将dummy结点考虑进去，防止空链表
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }
        ListNode head2 = prev; //head2是反转区间的前一个位置
        prev = prev.next; //prev是反转链表的最后一个结点，不断指向新的后继
        ListNode cur = prev.next; //cur是反转链表的第一个结点，不断添加到head之后
        //头插法
        for (int i = 0; i < n - m; i++) {
            //prev.next保存后方位置
            prev.next = cur.next;
            //反转指向，插入head2.next的位置
            cur.next = head2.next;
            //head2.next指向新的头部
            head2.next = cur;
            //移动指针到下一个插入位置
            cur = prev.next;
        }
        return dummy.next;
    }

    /**
     * 重排链表
     * https://leetcode-cn.com/problems/reorder-list/
     * 从首尾各取一个结点
     *
     * @param head
     */
    public void reOrderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        //首先，找到中点并分成两部分
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; //断开前半部分
        slow = reverseList(slow); //反转后半部分
        //将两个链表合并
        //A链表使用cur和tail保存相邻位置，B链表使用slow保存位置
        ListNode cur = head;
        ListNode tail;
        while (cur.next != null) {
            tail = cur.next; //保存后继结点
            cur.next = slow; //A -> B
            slow = slow.next; //保存后继结点
            cur.next.next = tail; //B -> A
            cur = tail; //移动指针
        }
        cur.next = slow;
    }

    /**
     * 两两交换链表中的节点
     * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        ListNode cur;
        ListNode next;
        while (prev.next != null && prev.next.next != null) {
            //根据prev找到后面两个结点
            cur = prev.next;
            next = cur.next;
            //交换两个结点，从后往前修改
            cur.next = next.next;
            next.next = cur;
            prev.next = next;
            //更新prev
            prev = cur;
        }
        return dummy.next;
    }

    /**
     * K 个一组翻转链表-递归-头插法
     * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head); //增加dummy结点
        return recur_reverse(dummy, k);
    }

    private ListNode recur_reverse(ListNode head, int k) {
        //选取K个结点
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            tail = tail.next;
            if (tail == null) { //不足的部分保持原来顺序
                return head.next;
            }
        }
        //递归处理后面的结点，先处理后面的然后连在前面
        tail.next = recur_reverse(tail, k);
        //头插法反转链表
        ListNode prev = head.next;
        ListNode cur = prev.next;
        for (int i = 0; i < (k - 1); i++) { //以次数作为判断依据
            prev.next = cur.next;
            cur.next = head.next;
            head.next = cur;
            cur = prev.next;
        }
        return head.next;
    }

    /**
     * K 个一组翻转链表-递归-直接反转
     * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }
        ListNode next_group = head; //先找到下一组的头结点
        for (int i = 0; i < k; i++) {
            if (next_group != null) { //不足的部分保持原来顺序
                next_group = next_group.next;
            } else {
                return head;
            }
        }
        ListNode new_next_group = reverseKGroup2(next_group, k);
        ListNode prev = null, cur = head, next;
        while (cur != next_group) { //以下一组的头结点作为判断依据
            next = cur.next;
            cur.next = prev != null ? prev : new_next_group; //直接反转注意第一个结点的特殊处理
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 使用归并排序排序链表
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        }
        //首先，找到中点并分成两部分
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        pre.next = null;
        //递归处理前后两部分
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(p);
        //归并操作
        return merge(l, r);
    }

    private ListNode merge(ListNode l, ListNode r) {
        ListNode dummy = new ListNode(-1, null);
        ListNode cur = dummy;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        if (l != null) {
            cur.next = l;
        }
        if (r != null) {
            cur.next = r;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(head);
        LinkedList list = new LinkedList();
        head = list.reverseList(head);
        System.out.println(head);
        head = list.reverseList2(head);
        System.out.println(head);
        head = list.reverseBetween(head, 1, 3);
        System.out.println(head);
        list.reOrderList(head);
        System.out.println(head);
        head = list.reverseKGroup(head, 3);
        System.out.println(head);
    }
}
