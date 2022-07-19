package search;

/**
 * @author linjie
 * @date 2020/07/17
 */
public class BinarySort {
    /**
     * 35. 搜索插入位置
     */
    public int searchInsert(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right){ //等于情况也需要考虑，因为这个位置没有比较过
            mid = left + (right - left) / 2;
            if (target < nums[mid]){
                right = mid - 1; //更新上下界时加减一
            }else if(target > nums[mid]){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return left;
    }

    /**
     * 69. x 的平方根
     */
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if ((long)mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 33. 搜索旋转排序数组
     * 二分查找变形
     * 如果在已知偏移点的情况下可以用下标偏移
     * 本题未知偏移点，所以要用另一种解法，处理有序部分
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchRotateArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                // 左部分有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右部分有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        // 查找第一个出现位置
        int first = findFirstPosition(nums, target);
        // 查找最后一个出现位置
        int last = findLastPosition(nums, target);
        return new int[]{first, last};
    }

    private int findFirstPosition(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                } else {
                    r = mid - 1;
                }
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    private int findLastPosition(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                if (mid == (nums.length - 1) || nums[mid + 1] != target) {
                    return mid;
                } else {
                    l = mid + 1;
                }
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}
