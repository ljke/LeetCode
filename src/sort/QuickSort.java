package sort;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 使用快排或快排思想解决问题
 */
public class QuickSort {
    /**
     * 75. 使用快排分区思想进行颜色分类
     * https://leetcode-cn.com/problems/sort-colors/
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int begin = 0, current = 0, end = nums.length - 1; //begin和end将数组分为3个区，分别为0, 1, 2
        while (current <= end) { //Note: 这里是<=
            if (nums[current] == 0) {
                //交换到begin的位置，然后begin和current后移
                swap(nums, begin, current);
                current++;
                begin++;
            } else if (nums[current] == 1) {
                //保持当前位置即可
                current++;
            } else if (nums[current] == 2) {
                //交换到end的位置，但是交换过来的元素需要判断，所以current位置不变
                swap(nums, current, end);
                end--;
            }
        }
    }

    /**
     * 交换数组中的两个元素
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
     * sortColor的另类解法，计算每种颜色的个数
     *
     * @param nums
     */
    public static void sortColors_alter(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        int red = -1, white = -1, blue = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) { //插入red对white blue有影响, 往后存
                nums[++blue] = 2;
                nums[++white] = 1;
                nums[++red] = 0;
            } else if (nums[i] == 1) {  //插入white对blue有影响, 往后存
                nums[++blue] = 2;
                nums[++white] = 1;
            } else {
                nums[++blue] = 2;
            }
        }
    }

    /**
     * 215. 寻找数组中的第K个最大元素
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
     * 使用快排的分区思想
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        return findKthLargest(nums, 0, len - 1, len - k + 1); //第k大的数等于第len - k + 1小的数
    }

    public static int findKthLargest(int[] nums, int begin, int end, int k) {
        if (begin == end && k == 1) { //当只有一个数时返回
            return nums[begin]; // 返回当前的起始元素
        }
        int pos = partition(nums, begin, end); //在区间内分区
        int len = pos - begin; //前半分区的长度
        if ((len + 1) == k) { //考虑分区点符合要求的情况，后面要将pivot排除
            return nums[pos];
        }
//        不能考虑前半分区长度符合要求的情况，因为前半分区未排序，这样会返回前半分区最后一个数
//        else if (len == k) {
//            return nums[pos - 1];
//        }
        else if ((len + 1) < k) { //当k超过len + 1时在后半区
            return findKthLargest(nums, pos + 1, end, k - len - 1); //在后半分区查找，k减少(前半分区的长度 + 1个pivot)
        } else {
            return findKthLargest(nums, begin, pos - 1, k); //在前半分区查找，右边界更新
        }
    }

    /**
     * 赋值填充法的分区函数
     *
     * @param nums
     * @param i
     * @param j
     * @return
     */
    public static int partition(int[] nums, int i, int j) {
        int pivot = nums[i];
        while (i < j) {
            while (i < j && nums[j] >= pivot)
                j--;
            nums[i] = nums[j];
            while (i < j && nums[i] <= pivot)
                i++;
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        return i;
    }

    /**
     * 找第K大数的另类解法，使用大小为K的堆，堆顶元素即为第K大数
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest_alter(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<>();
        for (int x : nums) {
            if (q.size() < k) {
                q.offer(x);
            } else {
                int top = q.peek();
                if (x > top) {
                    q.poll();
                    q.offer(x);
                }
            }
        }
        return q.peek();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{-1, 2, 0}, 2));
    }
}
