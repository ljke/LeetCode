package array;

/**
 * 31. 下一个排列
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        //step 1: 从后往前，寻找第一个违反升序的元素partitionNumber
        int p = len - 2;
        while (p >= 0 && nums[p] >= nums[p + 1]) {
            p--;
        }
        if (p == -1) { //特殊情况，说明当前序列已经是最大序列，反转整个数组，变为最小序列
            reverse(nums, 0, len - 1);
            return;
        }
        //step 2：从后往前，寻找第一个大于partitionNumber的元素changeNumber
        int c = len - 1;
        while (c > 0 && nums[c] <= nums[p]) {
            c--;
        }
        //step 3：交换partitionNumber和changeNumber，此时后面的序列依然满足从前往后降序
        swap(nums, p, c);
        //step 4：反转后面的序列为从前往后升序，因为这样的排列是最小的
        reverse(nums, p + 1, len - 1);
    }

    /**
     * 交换数组中的两个数
     *
     * @param nums
     * @param i
     * @param j
     */
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 反转数组元素，包含i和j
     *
     * @param nums
     * @param i
     * @param j
     */
    public static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        nextPermutation(new int[]{1, 1});
    }
}
