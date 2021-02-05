package pers.penglan.algorithm.jan2021;

/**
 * 简单正则表达时匹配
 * TODO 待完成，动态规划思想
 * @author penglanm@qq.com
 * 2021-01-20
 */
public class SimpleRegex {
    public static boolean isMatch(String s, String p) {
        char[] sc = new char[s.length()];
        if (s.length() > 0) {
            s.getChars(0, s.length(), sc, 0);
        }
        char[] pc = new char[p.length()];
        if (p.length() > 0) {
            p.getChars(0, p.length(), pc, 0);
        }

        /*先提前过滤一下两种可能的情况*/
        if ((pc.length == 0) && (sc.length == 0)) {
            return true;
        } else if (pc.length == 0) {
            return false;
        }

        int k = 0;
        for (int i = 0; i < pc.length;) {
            if (k < sc.length) {
                if (pc[i] == '.') {
                    k++;
                    i++;
                } else if (pc[i] == '*') {
                    if ((pc[i - 1] == sc[k]) || (pc[i - 1] == '.')) {
                        k++;
                    } else {
                        i++;
                    }
                } else {
                    if (pc[i] == sc[k]) {
                        k++;
                        i++;
                    } else {
                        if (((i + 1) < pc.length) && pc[i + 1] == '*') {
                            i += 2;
                        } else {
                            return false;
                        }
                    }
                }
            } else {
                if (pc[i] == '.') {
                    if (((i + 1) < pc.length) && pc[i + 1] == '*') {
                        i += 2;
                    } else {
                        return false;
                    }
                } else if (pc[i] == '*') {
                    i++;
                } else {
                    if (((i + 1) < pc.length) && pc[i + 1] == '*') {
                        i += 2;
                    } else {
                        return false;
                    }
                }
            }
        }

        return k >= sc.length;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("qbc", "abc"));
        System.out.println(isMatch("abcqr", ".*r"));
        System.out.println(isMatch("aab", "a*b*c*"));
        System.out.println(isMatch("httpcommoc", "ht*p.*m*oc"));
    }


}
