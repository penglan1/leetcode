package pers.penglan.algorithm.jan2021;

/**
 * 整数反转
 * @author penglanm@qq.com
 * 2021-01-18
 */
public class NumReverse {
    public static int reverse(int num) {
        byte[] bit1 = split(num);
        int reverseNum = reverse01(num);
        byte[] bit2 = split(reverseNum);

        /*0开始标志*/
        boolean zeroBeginFlag = false;
        for (int i = bit1.length - 1; i > -1 ; i--) {
            int j = bit1.length - i - 1;
            if (j < bit2.length) {
                if (bit2[j] != bit1[i]) {
                    return 0;
                }
            } else if (bit1[i] == 0 && !zeroBeginFlag) {
                zeroBeginFlag = true;
            } else if (bit1[i] == 0 && zeroBeginFlag) {

            } else {
                return 0;
            }
        }

        return reverseNum;
    }

    /**
     * 将num切分为对应的位数组（反序)
     * <pre>
     *     示例：
     *     split(123) --> [3, 2, 1]
     *     split(456000) --> [0, 0, 0, 6, 5, 4]
     * </pre>
     * @param num
     * @return
     */
    public static byte[] split(int num) {
        byte r = 10;
        byte[] numByte = new byte[50];
        int nextPos = 0;
        byte[] result = null;

        if (num == 0) {
            result = new byte[]{0};
            return result;
        }

        while (num != 0) {
            int t = num % r;
            numByte[nextPos++] = (byte) t;
            num /= r;
        }

        result = new byte[nextPos];
        System.arraycopy(numByte, 0, result, 0, nextPos);

        return result;
    }

    /**
     * 将num倒转
     * <pre>
     *     示例：
     *     reverse01(123000) --> 321
     *     reverse01(456789) --> 987654
     * </pre>
     * @param num
     * @return
     */
    private static int reverse01(int num) {
        int r = 10;
        int n = 10;
        int result = 0;

        if (num == 0) {
            return result;
        }

        while (num != 0) {
            int temp = num % r;
            result = result * n + temp;
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
