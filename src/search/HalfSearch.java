package search;

/**
 * 折半搜索
 *
 */
public class HalfSearch {

    private int[] digit = new int[100];

    /**
     * 2081. k 镜像数字的和
     * https://leetcode.cn/problems/sum-of-k-mirror-numbers/description/
     * 折半搜索，且能满足十进制回文要求，只需校验k进制是否回文
     *
     * @param k
     * @param n
     * @return
     */
    public long kMirror(int k, int n) {
        long left = 1;
        int count = 0;
        long ans = 0;
        while (count < n) {
            long right = left * 10;
            // 保证顺序：位数分段 先奇数 再偶数
            for (int op = 0; op <= 1; op++) {
                // 额外判断一下达到要求
                for (long i = left; i < right && count < n; i++) {
                    long cur = (op == 0) ? i / 10 : i;
                    long num = i;
                    while (cur > 0) {
                        num = num * 10 + cur % 10;
                        cur = cur / 10;
                    }
                    if (check(num, k)) {
                        count++;
                        ans += num;
                    }
                }
            }
            left = right;
        }
        return ans;
    }

    public boolean check(long num, int k) {
        int length = 0;
        while (num > 0) {
            digit[length] = (int) (num % k);
            length++;
            num = num / k;
        }
        for (int i = 0; i < length / 2; i++) {
            if (digit[i] != digit[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

}
