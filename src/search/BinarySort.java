package search;

/**
 * @author linjie
 * @date 2020/07/17
 */
public class BinarySort {
    /**
     * 35. 搜索插入位置
     */
    public static int searchInsert(int[] nums, int target){
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

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 2;
        System.out.println(searchInsert(nums, target));
    }
}
