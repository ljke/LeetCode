package linkedlist;

/**
 * 138. 复制带随机指针的链表
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * 包含随机指针的单向链表
 * 链表的深拷贝方法
 */
public class RandomList {
    static class RandomListNode {
        public int val;
        public RandomListNode next;
        public RandomListNode random;

        public RandomListNode(int val) {
            this.val = val;
        }

        /**
         * 重写toString方法用于打印列表信息
         * 注意判断这里的random指针可能为null
         *
         * @return
         */
        @Override
        public String toString() {
            if (next == null) {
                return "(" + null + "," +
                        (random != null ? Integer.toString(random.val) : null) + ")";
            } else {
                return "(" + this.hashCode() + "," + val + "," +
                        (random != null ? Integer.toString(random.val) : null) + ")" +
                        "->" + next.toString();
            }
        }
    }

    /**
     * 随机指针链表的深拷贝方法
     * 新建重复结点交叉插入链表中，然后进行拆分
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode cur = head;
        RandomListNode node;
        //复制链表
        while (cur != null) {
            //1. 创建一个复制链表新结点，val等于原始链表的值
            node = new RandomListNode(cur.val);
            //2. 将新结点插入cur之后
            node.next = cur.next;
            cur.next = node;
            //3. 移动cur到原始链表上的下一个结点
            cur = node.next;
        }

        //重建random指向
        cur = head;
        while (cur != null) {
            //1. 找到复制链表上的结点
            node = cur.next;
            //2. 拷贝原始链表上的random指向关系
            if (cur.random != null) {
                node.random = cur.random.next;
            }
            //3. 移动cur到原始链表上的下一个结点
            cur = cur.next.next;
        }

        //拆分链表
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode new_cur = dummy;
        cur = head;
        while (cur != null) {
            //先完成复制链表的连接
            new_cur.next = cur.next;
            new_cur = new_cur.next;
            //再完成原始链表的连接
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        RandomListNode head = new RandomListNode(1);
        RandomListNode head1 = new RandomListNode(2);
        RandomListNode head2 = new RandomListNode(3);
        RandomListNode head3 = new RandomListNode(4);
        RandomListNode head4 = new RandomListNode(5);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head2.random = head1;
        head4.random = head3;
        System.out.println(head);
        RandomList list = new RandomList();
        head = list.copyRandomList(head);
        System.out.println(head);
    }
}
