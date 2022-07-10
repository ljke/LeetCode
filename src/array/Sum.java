package array;

import java.util.*;

/**
 * 求和问题
 */
public class Sum {
    /**
     * 1. 两数之和
     * 取列表nums中的两个数，和等于target
     * https://leetcode-cn.com/problems/two-sum/
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> myMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (myMap.containsKey(nums[i])) {
                return new int[]{myMap.get(nums[i]), i};
            } else {
                myMap.put(target - nums[i], i); //存入差值
            }
        }
        return null;
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     * 双指针缩小查找范围，不会出现错过正确位置的情况
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int total;
        while (i < j) {
            total = numbers[i] + numbers[j];
            if (total == target) {
                break;
            } else if (total < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{i + 1, j + 1}; //返回序号从1开始
    }

    /**
     * 15.取列表中的三个数，和等于0
     * https://leetcode.cn/problems/3sum/
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3)
            return result;
        Arrays.sort(nums);
        int target = 0; //目标为0
        for (int i = 0; i < (nums.length - 2); i++) {
            if (i > 0 && nums[i] == nums[i - 1]) //跳过相同的值
                continue;
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < target) {
                    j++;
                    while (nums[j] == nums[j - 1] && j < k) //在左右夹逼时也要注意跳过相同的值
                        j++;
                } else if (nums[i] + nums[j] + nums[k] > target) {
                    k--;
                    while (nums[k] == nums[k + 1] && j < k)
                        k--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (nums[j] == nums[j - 1] && j < k)
                        j++;
                    while (nums[k] == nums[k + 1] && j < k)
                        k--;
                }
            }
        }
        return result;
    }

    /**
     * 取列表中的三个数，和最接近于target
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int result = 0; //保存结果
        int minGap = Integer.MAX_VALUE; //保存差值
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int gap = Math.abs(sum - target);
                if (gap < minGap) { //更新最小差值
                    result = sum;
                    minGap = gap;
                }
                if (sum < target)
                    j++;
                else
                    k--;
            }
        }
        return result;
    }

    /**
     * 取列表中的四个数，和等于target
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4)
            return result;
        Arrays.sort(nums);

        HashMap<Integer, ArrayList<int[]>> cache = new HashMap<>(); //使用HashMap缓存两数之和, key为和，value为相加数的列表
        for (int i = 0; i < nums.length; i++) {
            //不能跳过重复的数，否则结果会丢失，因为HashMap缓存4个数中的后2个，可能使用位于后面的重复数值，需要保存所有的下标
//            if(i > 0 && nums[i - 1] == nums[i])
//                continue;
            for (int j = i + 1; j < nums.length; j++) {
//                if(j > i + 1 && nums[j - 1] == nums[j])
//                    continue;
                ArrayList<int[]> value = cache.computeIfAbsent(nums[i] + nums[j], k -> new ArrayList<>());
                value.add(new int[]{i, j});
            }
        }
        HashSet<String> used = new HashSet<>(); //使用set跳过重复情况，i和j是不会重复的，但是后两个数可能重复
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) //跳过重复的数
                continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                ArrayList<int[]> list = cache.get(target - nums[i] - nums[j]);
                if (list == null)
                    continue;
                for (int[] pair : list) {
                    if (j >= pair[0]) //i, j作为前两个数，跳过其他重复情况
                        continue;
                    Integer[] sol = new Integer[]{nums[i], nums[j], nums[pair[0]], nums[pair[1]]};
                    //Arrays.sort(sol); //已经是拍好序的了
                    String key = Arrays.toString(sol);
                    if (!used.contains(key)) {
                        result.add(Arrays.asList(sol));
                        used.add(key);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0};
        int target = 0;
        Sum sum = new Sum();
        List<List<Integer>> list = sum.fourSum(nums, target);
        for (List<Integer> item : list) {
            System.out.println(item);
        }
    }
}
