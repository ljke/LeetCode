package linkedlist;

public class ListNode {
    /**
     * 结点类
     */
    public int val;
    public ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        if (this.next == null) {
            return this.val + "->" + "NULL";
        } else {
            return this.val + "->" + this.next.toString();
        }
    }
}
