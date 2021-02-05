package pers.penglan.algorithm.jan2021;

/**
 * {@link NumReverse}的优化改进（力扣官方解决方式）
 * @author penglanm@qq.com
 * 2021-01-19
 */
public class NumReverseLC {
    public static int reverse(int num) {
        int result = 0;
        int r = 10;
        int pe = Integer.MAX_VALUE % r;
        int ne = Integer.MIN_VALUE % r;

        if (num == 0) {
            return 0;
        }

        while (num != 0) {
            int temp = num % r;
            if (num > 0) {
                /*提前判断是否会出现溢出情况*/
                if (result > (Integer.MAX_VALUE / r)) {
                    return 0;
                } else if ((result == (Integer.MAX_VALUE / 10)) && (temp > pe)){
                    return 0;
                }
            } else {
                /*提前判断是否会出现溢出情况*/
                if (result < (Integer.MIN_VALUE / r)) {
                    return 0;
                } else if ((result == (Integer.MIN_VALUE / 10)) && (temp < ne)){
                    return 0;
                }
            }
            result = result * r + temp;
            num /= r;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(Integer.MAX_VALUE * (-1)));
        System.out.println(reverse(987654321));
        System.out.println(reverse(2147481200));
    }
}
