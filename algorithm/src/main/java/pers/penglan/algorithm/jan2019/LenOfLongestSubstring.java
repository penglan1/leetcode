package pers.penglan.algorithm.jan2019;

import java.util.HashMap;
import java.util.Map;

/**
 * 求一个字符串的最大字串的长度
 */
public class LenOfLongestSubstring {
    public static void main(String[] args) {
        String test = "pwwkew";
        int len = new LenOfLongestSubstring().lengthOfLongestSubstring(test);
        System.out.println(len);
    }

    public int lengthOfLongestSubstring(String s) {
        return lengthOfLongestSubstring01(s);
    }

    private int lengthOfLongestSubstring01(String s) {
        int maxLen = 0;  // 当前最大字串的的长度
        int begin = 0;  // 字串的开始索引
        Map<Character, Integer> map = new HashMap<>(26);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {  // 如果存在重复的字符
                if (map.size() > maxLen)
                    maxLen = map.size();
                /**
                 * 从map中去除重复字符之前的所有字符，以便新的字串查找
                 */
                int position = map.get(c);
                for (int j = begin; j <= position; j++) {
                    map.remove(s.charAt(j));
                }
                begin = position + 1;
            }
            map.put(s.charAt(i), i);

        }
        if (map.size() > maxLen)
            maxLen = map.size();

        return maxLen;
    }
}
