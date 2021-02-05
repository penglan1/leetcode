package pers.penglan.algorithm.jan2021;

/**
 * {@link Palindrome}优化改进（力扣官方解决方式）
 * @author penglanm@qq.com
 * 2021-01-20
 */
public class PalindromeLC {
    public static boolean isPalindrome(int x) {
        /*提前过滤不可能的情况*/
        if (x < 0 || (x % 10 == 0)) {
            return false;
        }

        return x == reverse(x);
    }

    /**
     * 数子倒转（此处无需判断溢出）
     * @param num
     * @return
     */
    private static int reverse(int num) {
        int r = 10;
        int result = 0;

        if (num == 0) {
            return 0;
        }

        while (num != 0) {
            int temp = num % r;
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
        System.out.println(isPalindrome(-12321));
    }
}
