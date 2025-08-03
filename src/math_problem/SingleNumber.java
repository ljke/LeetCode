package math_problem;

/**
 * @author : ljke
 * @description : 二进制位运算技巧题
 * @date : Created in 21:21 2025/7/16
 */
public class SingleNumber {

    /**
     * 136. 只出现一次的数字
     * https://leetcode.cn/problems/single-number/description/
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int n : nums) {
            // 相同数异或为0 且满足交换律 最后剩下的就是只出现一次的数字
            single ^= n;
        }
        return single;
    }

}
