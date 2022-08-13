package array;

import java.util.*;

/**
 * 存在重复元素问题
 */
public class ContainDuplicate {
    /**
     * 是否存在重复元素
     *
     * @param nums
     * @return 只要有重复，返回true
     */
    public boolean containsDuplicate(int[] nums) {
        //判断是否在set当中
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return true;
            } else {
                set.add(n);
            }
        }
        return false;
    }

    /**
     * 在指定间隔内存在重复元素
     *
     * @param nums
     * @param k    指定间隔
     * @return
     */
    public boolean containNearbyDuplicate(int[] nums, int k) {
        if(k < 1){
            return false;
        }
        //使用map保存元素和位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                } else {
                    map.put(nums[i], i); //不符合时要更新位置
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    /**
     * 在指定间隔内指定差值内的元素
     *
     * @param nums
     * @param k    指定间隔
     * @param t    指定差值
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>(); //使用二叉查找树保存k个数，可以很快找出最大下限和最小上限
        int x;
        Integer floor, ceiling;
        for (int i = 0; i < nums.length; i++) {
            x = nums[i];
            floor = set.floor(x); //小于等于x的最大数
            ceiling = set.ceiling(x); //大于等于x的最小数
            if ((floor != null && x <= floor + t) || (ceiling != null && x >= ceiling - t)) { //当前值在靠近范围内
                return true;
            }
            set.add(x);
            if (i >= k) {
                set.remove(nums[i - k]); //使用TreeSet保存一个滑动窗口，保持二叉查找树中只有k个元素
            }
        }
        return false;
    }

}
