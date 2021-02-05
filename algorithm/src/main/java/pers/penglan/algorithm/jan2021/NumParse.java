package pers.penglan.algorithm.jan2021;

/**
 * 字符数子解析
 * @author penglanm@qq.com
 * 2021-01-19
 */
public class NumParse {

    public static int myAtoi(String s) {
        int result = 0;
        int symbol = 1;
        int r = 10;
        boolean beginFlag = false;
        char[] sc = null;

        if (s.equals("")) {
            return 0;
        }

        sc = new char[s.length()];
        s.getChars(0, s.length(), sc, 0);

        for (int i = 0; i < sc.length; i++) {
            if (sc[i] == ' ') {
                if (beginFlag) {
                    return symbol * result;
                }
            } else if (sc[i] == '-') {
                if (beginFlag) {
                    return symbol * result;
                } else {
                    symbol = -1;
                    beginFlag = true;
                }
            } else if (sc[i] == '+') {
                if (beginFlag) {
                    return symbol * result;
                } else {
                    symbol = 1;
                    beginFlag = true;
                }
            } else if (('0' <= sc[i]) && (sc[i] <= '9')) {
                if (!beginFlag) {
                    result = sc[i] & 0XF;
                    beginFlag = true;
                } else {
                    if (checkOverflow(symbol * result, symbol * (sc[i] & 0XF))) {
                        return (symbol > 0) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    }
                    result = result * 10 + (sc[i] & 0XF);
                }
            } else {
                return symbol * result;
            }
        }

        return symbol * result;
    }

    /**
     * 判断num再下一次整合时是否会溢出
     * @param num
     * @param append
     * @return
     */
    private static boolean checkOverflow(int num, int append) {
        int pe = Integer.MAX_VALUE % 10;
        int ne = Integer.MIN_VALUE % 10;

        if (num == 0) {
            return false;
        }
        if (num > 0) {
            if (num > (Integer.MAX_VALUE / 10)) {
                return true;
            } else if (num == (Integer.MAX_VALUE / 10) && append >= pe) {
                return true;
            }
        } else {
            if (num < (Integer.MIN_VALUE / 10)) {
                return true;
            } else if (num == (Integer.MIN_VALUE / 10) && append <= ne) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("123"));
        System.out.println(myAtoi("   -234234fneoi3233"));
        System.out.println(myAtoi(""));
        System.out.println(myAtoi("   "));
        System.out.println(myAtoi("-88888888888888888"));
        System.out.println(myAtoi("  1230808408098409809809809"));
        System.out.println(myAtoi(" -42"));
        System.out.println(myAtoi("+1"));
        System.out.println(myAtoi("00000-42a1234"));
        System.out.println(myAtoi("  0000000000012345678"));
        System.out.println(myAtoi("0  123"));
        System.out.println(myAtoi("   +0 123"));

    }
}
