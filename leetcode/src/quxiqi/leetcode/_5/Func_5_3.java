package quxiqi.leetcode._5;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 中心点，易于理解的逻辑。
 * @date 2019/3/23 17:10
 **/
public class Func_5_3 implements Run5.Func_5{

    @Override
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int maxLen = 0, start = 0, end = 0;
        for (int i = 0; i < chars.length - maxLen/2; i++) {
            int len1 = center(i, i, chars);
            int len2 = center(i, i + 1, chars);
            int len = len1 > len2 ? len1 : len2;
            if (len > maxLen) {
                start = i - (len-1)/2;
                end = i + len/2;
                maxLen = len;
            }
        }

        return s.substring(start, end + 1);
    }

    private int center(int left, int right, char[] chars) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left --;
            right ++;
        }
        return right - left - 1;
    }
}
