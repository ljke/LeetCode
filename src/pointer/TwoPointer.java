package pointer;

import linkedlist.ListNode;

/**
 * 双指针相关
 */
public class TwoPointer {

    /**
     * 283. 移动零
     * https://leetcode.cn/problems/move-zeroes/description/
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int p = 0;
        int cur = 0;

        // 要保证顺序，必须从前往后填充非零元素
        // p表示当前填充位置
        while (cur < nums.length) {
            if (nums[cur] != 0) {
                swap(nums, cur, p);
                p++;
                cur++;
            } else {
                cur++;
            }
        }

    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    /**
     * 11. 盛最多水的容器
     * https://leetcode.cn/problems/container-with-most-water/
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        int l = 0, r = height.length - 1;
        while(l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            max = Math.max(max, area);
            // 排除掉当前小的这个边界，移动短边
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

    /**
     * 5. 最长回文子串
     * https://leetcode.cn/problems/longest-palindromic-substring/description/
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 分两种情况遍历 奇数长度/偶数长度
            String s1 = palindrome(i, i, s);
            String s2 = palindrome(i, i + 1, s);
            res = s1.length() > res.length() ? s1 : res;
            res = s2.length() > res.length() ? s2 : res;
        }
        return res;
    }

    public String palindrome(int l, int r, String s) {
        // 双指针判断
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        // 考虑退出条件前的坐标
        return s.substring(l + 1, r);
    }

    /**
     * 160. 相交链表
     * https://leetcode.cn/problems/intersection-of-two-linked-lists/description/
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA;
        ListNode p2 = headB;
        // 到达链表尾部时跳跃到另一个链表
        // 假设headA和headB相交部分长度为c，不相交部分长度为a和b
        // 这样在跳跃a + b + c次后，会指向相交点
        while (p1 != p2) {
            p1 = (p1 != null) ? p1.next : headB;
            p2 = (p2 != null) ? p2.next : headA;
        }

        return p1;
    }

}
