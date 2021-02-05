package pers.penglan.algorithm.jan2019;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串数据相乘
 */
public class StringMultiply {
    public static void main(String[] args) {
        String num1 = "1234567890";
        String num2 = "456789";
        String sum3 = new StringMultiply().multiply(num1, num2);
        System.out.println(sum3);
    }

    public String multiply(String num1, String num2) {
        return multiply01(num1, num2);
    }

    private String multiply01(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2))
            return "0";

        int[] aNum1 = castToArray(num1);
        int[] aNum2 = castToArray(num2);
        int[] sum3 = multiply02(aNum1, aNum2);
        return reverseToString(sum3);
    }

    private int[] multiply02(int[] num1, int[] num2) {
        List<Integer> list = new ArrayList<>(num1.length + num2.length);
        for (int i = 0; i < num2.length; i++) {
            int offset = i;  // 起始偏移量，即首次相加对齐的位置
            for (int j = 0; j < num1.length; j++) {
                multiply03(list, num2[i], num1[j], offset + j);
            }
        }

        Integer[] arrs = list.toArray(new Integer[0]);
        int[] rst = new int[arrs.length];
        for (int i = 0; i < arrs.length; i++) {
            rst[i] = arrs[i];
        }

        return rst;
    }

    /**
     * 带有自动进位的单个数相乘
     * @param list
     * @param data1
     * @param data2
     * @param index  乘积放入的位置
     */
    private void multiply03(List<Integer> list, int data1, int data2, int index) {
        int m = data1 * data2;
        add(list, m, index);
    }

    private void add(List<Integer> list, int data, int index) {
        if (index == list.size()) {
            list.add(0);
        }
        int sum = list.get(index) + data;
        int c = sum > 9 ? sum % 10 : sum;  // 当前位的值：如果大于9，则是进位之后的值，否则不用进位
        list.set(index, c);
        if (sum > 9) {  // 处理进位
            if (list.size() == index + 1) {
                list.add(0);
            }
            add(list, sum / 10, index + 1);
        }
    }

    private String reverseToString(int[] num) {
        StringBuilder builder = new StringBuilder(num.length);
        for (int i = num.length - 1; i >= 0 ; i--) {
            builder.append(num[i]);
        }

        return builder.toString();
    }

    /**
     * 将对应的字符串数据用数组表示
     * 注意：数组对应的<Strong>位</Strong>顺序是相反的
     * EG:
     * <code>
     *     int[] num = castToArray("12345"); // num[5, 4, 3, 2, 1]
     * </code>
     * @param num
     * @return
     */
    private int[] castToArray(String num) {
        int[] data = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            data[data.length - 1 - i] = (num.charAt(i) & 0X000F);  // 将字符变为对应的数字
        }

        return data;
    }


}
