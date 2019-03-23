package quxiqi.leetcode._5;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 纯暴力解法
 * @date 2019/3/23 15:33
 **/
public class Func_5_1 implements Run5.Func_5 {

    @Override
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int maxStart = -1, maxEnd = -1;
        for (int i = 0; i < chars.length; i++) {
            int[] ints = find(i, chars.length - 1, chars);
            if (ints != null) {
                if (maxEnd - maxStart  < ints[1] - ints[0] || maxStart < 0) {
                    maxStart = ints[0];
                    maxEnd = ints[1];
                }
            }
        }
        String str;
        if (maxStart < 0) {
            str = new String(new char[]{chars[0]});
        } else {
            str = s.substring(maxStart, maxEnd + 1);
        }
        return str;
    }

    private int[] find(int start, int end, char[] chars) {
        for (int i = end; i >= start; i--) {
            if (ok(start, i, chars)) {
                return new int[] {start, i};
            }
        }
        return null;
    }

    private boolean ok(int start, int end, char[] chars) {
        for (int i = start, j = end; i < j; i++,j--) {
            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }
}
