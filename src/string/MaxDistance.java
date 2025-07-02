package string;

/**
 * 3443. K 次修改后的最大曼哈顿距离
 * https://leetcode.cn/problems/maximum-manhattan-distance-after-k-changes/description/
 */
public class MaxDistance {

    /**
     * 两次遍历，分步求解
     *
     * @param s
     * @param k
     * @return
     */
    public int maxDistance1(String s, int k) {
        int ans = 0;
        int north = 0, south = 0, east = 0, west = 0;
        for (char c : s.toCharArray()) {
            switch (c) {
                case 'N':
                    north++;
                    break;
                case 'S':
                    south++;
                    break;
                case 'E':
                    east++;
                    break;
                case 'W':
                    west++;
                    break;
                default:
                    break;
            }
            // 修改"数量少的方向"能增大距离
            // 两次遍历
            int time1 = Math.min(Math.min(north, south), k);
            int time2 = Math.min(Math.min(east, west), k - time1);
            // 随移动过程计算最大值
            ans = Math.max(ans, count(north, south, time1) + count(east, west, time2));
        }
        return ans;
    }

    public int count(int drt1, int drt2, int t) {
        return Math.abs(drt1 - drt2) + t * 2;
    }

    /**
     * 一次遍历，计算较大值
     *
     * @param s
     * @param k
     * @return
     */
    public int maxDistance2(String s, int k) {
        int ans = 0;
        int latitude = 0, longitude = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'N':
                    latitude++;
                    break;
                case 'S':
                    latitude--;
                    break;
                case 'E':
                    longitude++;
                    break;
                case 'W':
                    longitude--;
                    break;
                default:
                    break;
            }
            // 1. k < 所有“数量少的方向”的变换，结果=变换后的结果(一定小于字符串长度)
            // 2. k >= 所有“数量少的方向”的变换，结果=字符串长度(一定小于变换后的长度)
            int cur = Math.min(Math.abs(latitude) + Math.abs(longitude) + k * 2, i + 1);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

}
