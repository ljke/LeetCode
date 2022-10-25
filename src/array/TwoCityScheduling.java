package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1029. 两地调度
 * https://leetcode-cn.com/problems/two-city-scheduling/
 */
public class TwoCityScheduling {
    public static int twoCitySchedCost(int[][] costs) {
        //初始化列表，让每个人先去近的城市
        int countA = 0;
        int countB = 0;
        int sum = 0;
        List<Integer> AtoB = new ArrayList<>();
        List<Integer> BtoA = new ArrayList<>();
        for (int[] person : costs) {
            if (person[0] <= person[1]) {
                countA++;
                sum += person[0];
                AtoB.add(person[1] - person[0]);
            } else {
                countB++;
                sum += person[1];
                BtoA.add(person[0] - person[1]);
            }
        }
        //调整人数，使得两个城市人数相等
        int diff = countA - countB;
        if (diff > 0) {
            sum += AtoB.stream().sorted().limit(diff / 2).mapToInt((x) -> x).sum();
        } else if (diff < 0) {
            sum += BtoA.stream().sorted().limit(-diff / 2).mapToInt((x) -> x).sum();
        }
        return sum;
    }

    public static int twoCitySchedCost_opti(int[][] costs) {
        //计算去A地和去B地的费用差，然后按照费用差排序
        Arrays.sort(costs, Comparator.comparingInt(o -> (o[0] - o[1])));
        //前半部分去A地，后一半人去B地
        int len = costs.length / 2;
        int sum = 0;
        sum += Arrays.stream(costs).limit(len).mapToInt((x) -> x[0]).sum();
        sum += Arrays.stream(costs).skip(len).limit(len).mapToInt((x) -> x[1]).sum();
        return sum;
    }

    public static void main(String[] args) {
        int[][] costs = {{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}};
        System.out.println("Min cost:" + twoCitySchedCost(costs));
        System.out.println("Min cost:" + twoCitySchedCost_opti(costs));
    }
}
