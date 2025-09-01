package array;

/**
 * @author : ljke
 * @date : Created in 20:42 2025/8/26
 */
public class CanCompleteCircuit {

    /**
     * 134. 加油站
     * https://leetcode.cn/problems/gas-station/description/
     *
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int start = 0;
        while (start < n) {
            int remainGas = 0;
            int i = 0;
            for (; i < n; i++) {
                int index = (start + i) % n;
                remainGas += gas[index];
                remainGas -= cost[index];
                if (remainGas < 0) {
                    break;
                }
            }
            if (i == n) {
                // 遍历完一圈
                return start;
            } else {
                // 无法遍历一圈，从下个位置开始重新考虑，之前的位置都不能满足
                start = start + i + 1;
            }
        }
        return -1;
    }

}
