package pers.penglan.algorithm.jan2021;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 区间合并
 * @author penglanm@qq.com
 * 2021-01-23
 */
public class NumGapMerge {
    public int[][] merge(int[][] intervals) {
        LinkedList<Integer[]> cr = new LinkedList<>();
        for (int[] a : intervals) {
            float left = -1;
            float right = -1;
            for (int i = 0; i < cr.size(); i++) {
                if (left == -1) {
                    if (cr.get(i)[0] > a[0]) {
                        left = i - 0.5F;
                    } else if ((cr.get(i)[0] <= a[0]) && (a[0] <= cr.get(i)[1])) {
                        left = i;
                    } else if ((i + 1) < cr.size()) {
                        if (a[0] < cr.get(i + 1)[0]) {
                            left = i + 0.5F;
                        }
                    } else {
                        left = i + 0.5F;
                    }
                }
                if (right == -1) {
                    if (cr.get(i)[0] > a[1]) {
                        right = i - 0.5F;
                    } else if ((cr.get(i)[1] >= a[1]) && (cr.get(i)[0] <= a[1])) {
                        right = i;
                    } else if ((i + 1) < cr.size()) {
                        if (a[1] < cr.get(i + 1)[0]) {
                            right = i + 0.5F;
                        }
                    } else {
                        right = i + 0.5F;
                    }
                }
            }

            /*区间合并*/
            if (cr.size() == 0) {
                cr.addFirst(new Integer[]{a[0], a[1]});
                continue;
            }
            Integer[] temp = new Integer[2];
            temp[0] = ((left % 1) == 0) ? cr.get((int) left)[0] : a[0];
            temp[1] = ((right % 1) == 0) ? cr.get((int) right)[1] : a[1];
            /*计算待移除的区间*/
            int dl = ((left % 1) == 0) ? (int) left : (int) (left + 0.5F);
            int dr = ((right % 1) == 0) ? (int) right + 1 : (int) (right + 0.5);
            cr.subList(dl, dr).clear();
            /*添加新区间*/
            cr.add(dl, temp);
        }

        int[][] result = new int[cr.size()][2];
        for (int i = 0; i < cr.size(); i++) {
            result[i][0] = cr.get(i)[0];
            result[i][1] = cr.get(i)[1];
        }

        return result;
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
