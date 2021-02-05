package pers.penglan.algorithm.jan2021;

/**
 * 检测字符串t是否是字符串s的字母异位词
 * @author penglanm@qq.com
 * 2021-01-23
 */
public class AlphabetDisorder {
    public boolean isAnagram(String s, String t) {
        /*统计个字符的数量*/
        int[] sa = generateStatisticsChars(s);
        /*进行消除*/
        for (int i = 0; i < t.length(); i++) {
            int c = t.charAt(i) & 0X1F;
            if (sa[c] > 0) {
                sa[c]--;
            } else {
                return false;
            }
        }

        /*如有剩余，返回false*/
        for (int i : sa) {
            if (i > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 对s中的各字符的数量进行统计
     * @param s
     * @return
     */
    private int[] generateStatisticsChars(String s) {
        int[] statisticBytes = new int[27];
        /*初始化*/
        for (int i = 0; i < statisticBytes.length; i++) {
            statisticBytes[i] = 0;
        }
        /*统计*/
        for (int i = 0; i < s.length(); i++) {
            statisticBytes[(s.charAt(i) & 0X1F)]++;
        }

        return statisticBytes;
    }

    public static void main(String[] args) {
        AlphabetDisorder ad = new AlphabetDisorder();
        System.out.println(ad.isAnagram("asdfg", "asdg"));
        System.out.println(ad.isAnagram("asd", "das"));
        System.out.println(ad.isAnagram("qwert", "qwwertt"));
        System.out.println(ad.isAnagram("hjkl", "hjkl"));

    }
}
