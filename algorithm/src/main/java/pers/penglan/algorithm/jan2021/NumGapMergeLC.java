package pers.penglan.algorithm.jan2021;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * {@link NumGapMerge}的优化改进（力扣官方解决方式）
 * @author penglanm@qq.com
 * 2021-01-24
 */
public class NumGapMergeLC {
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> cr = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> {return (a[0] <= b[0]) ? -1 : 1;});
        for (int[] t : intervals) {
            if (cr.isEmpty()) {
                cr.add(new int[]{t[0], t[1]});
                continue;
            }
            if (cr.getLast()[1] >= t[0]) {
                cr.getLast()[1] = Math.max(cr.getLast()[1], t[1]);
            } else {
                cr.addLast(new int[]{t[0], t[1]});
            }
        }

        return cr.toArray(new int[0][2]);
    }

    public static void main(String[] args) {
        NumGapMerge nm = new NumGapMerge();
        int[][] test = new int[5][2];
        test[0] = new int[]{2, 3};
        test[1] = new int[]{5, 5};
        test[2] = new int[]{2, 2};
        test[3] = new int[]{3, 4};
        test[4] = new int[]{3, 4};
        System.out.println(Arrays.deepToString(nm.merge(test)));
    }
}
