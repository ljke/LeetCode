package linkedlist;

/**
 * 删除排序链表中的重复元素
 */
public class DeleteDuplicate {
    /**
     * 删除重复，只留一个
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        //判断空链表
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (prev.val == cur.val) { //比较值是否相等
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = prev.next; //cur更新
        }
        return head;
    }

    /**
     * 删除重复，只留不重复的
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        //判断空链表
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(Integer.MAX_VALUE); //dummy结点作为新链表头结点
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = dummy.next;
        boolean duplicated; //标记是否重复
        while (cur != null) {
            duplicated = false;
            while (cur.next != null && cur.val == cur.next.val) { //使用前先判断null
                duplicated = true;
                cur = cur.next;
            }
            if (duplicated) {
                //如果有重复，跳过所有重复结点
                prev.next = cur.next;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
