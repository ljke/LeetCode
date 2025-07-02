package pointer;

/**
 * 快慢指针相关
 */
public class FastSlowPointer {

    /**
     * 202. 快乐数
     * https://leetcode.cn/problems/happy-number/description/
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        // 快慢指针相遇说明形成环
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    // 获取下个节点
    public int getNext(int n) {
        int total = 0;
        while (n > 0) {
            int d = n % 10;
            total = total + d * d;
            n = n / 10;
        }
        return total;
    }

}
