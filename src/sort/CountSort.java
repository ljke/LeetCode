package sort;

public class CountSort {
    /**
     * 274. H指数
     * https://leetcode-cn.com/problems/h-index/
     * 使用计数排序查找至多有 h 篇论文分别被引用了至少 h 次
     */
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] count = new int[len + 1]; //下标范围为0~len，表示引用次数
        for (int citation : citations) {
            int val = citation >= len ? len : citation; //将大于len的数视为len
            count[val]++;
        }
        int sum = 0; //sum记录大于等于当前val的总和
        for (int i = len; i > 0; i--) {
            sum += count[i]; //累加
            if(sum >= i){ //边界条件，总和大于引用次数
                return i;
            }
        }
        return 0;
    }
}
