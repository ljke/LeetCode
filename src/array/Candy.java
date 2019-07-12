package array;

/**
 * 135. 分发糖果
 * https://leetcode-cn.com/problems/candy/
 */
public class Candy {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] increment = new int[len];
        //从左边遍历一遍，计算需要额外的糖果
        for (int i = 1, incr = 1; i < len; i++) {
            if (ratings[i - 1] < ratings[i]) {
                increment[i] = Math.max(increment[i], incr++); //大于时incr赋值后增加
            } else {
                incr = 1; //小于时incr重置
            }
        }
        //从右边遍历一遍，计算需要额外的糖果
        for (int i = (len - 2), incr = 1; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                increment[i] = Math.max(increment[i], incr++);
            } else {
                incr = 1;
            }
        }
        //总糖果数等于每人1个 + 额外的糖果
        int sum = len;
        for (int i = 0; i < len; i++) {
            sum += increment[i];
        }
        return sum;
    }
}
