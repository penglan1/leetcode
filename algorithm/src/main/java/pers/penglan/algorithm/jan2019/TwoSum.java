package pers.penglan.algorithm.jan2019;

import java.util.Arrays;

/**
 * 两数之和
 * @author penglan
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {0, 4, 3, 0};
        int[] result = new TwoSum().twoSum(nums, 0);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] nums, int target) {
        return find(nums, 0, nums.length - 1, target);
    }
    /**
     利用分治思想，递归求解
     */
    private int[] find(int[] nums, int low, int high, int target) {
        /** 不可再分割了 */
        if (high <= low)
            return null;

        /** 查看左半部分 */
        int mid = (low + high) / 2;
        int[] result = find(nums, low, mid, target);
        /** 目标找到，直接返回 */
        if (result != null)
            return result;

        /** 查看右半部分 */
        result = find(nums, mid + 1, high, target);
        /** 目标找到，直接返回 */
        if (result != null)
            return result;

        /** 检查左、右两部分的组合 */
        for (int i = low; i <= mid; i++) {
            for (int j = mid + 1; j <= high; j++)
                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
        }

        /** 未找到符合的结果 */
        return null;

    }
}
