package pers.penglan.algorithm.jan2021;

/**
 * 判断一个数子是否为回文数
 * @author penglanm@qq.com
 * 2021-01-20
 */
public class Palindrome {
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        return x == reverse(x);
    }

    private static int reverse(int num) {
        int r = 10;
        int result = 0;
        int pe = Integer.MAX_VALUE % r;
        int ne = Integer.MIN_VALUE % r;

        if (num == 0) {
            return 0;
        }

        while (num != 0) {
            int temp = num % r;
            if (num > 0) {
                if (result > (Integer.MAX_VALUE / r)) {
                    return 0;
                } else if ((result == (Integer.MAX_VALUE / r)) && temp > pe) {
                    return 0;
                }
            } else {
                if (result < (Integer.MIN_VALUE / r)) {
                    return 0;
                } else if ((result == (Integer.MIN_VALUE / r)) && temp < ne) {
                    return 0;
                }
            }
            result = result * r + temp;
            num /= r;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(123));
        System.out.println(isPalindrome(798798798));
        System.out.println(isPalindrome(123321));
        System.out.println(isPalindrome(121));
    }
}
