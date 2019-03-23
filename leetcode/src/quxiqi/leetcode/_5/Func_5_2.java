package quxiqi.leetcode._5;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 找中心点，现在的代码逻辑比较难理解，处理特殊边界的逻辑过多，看下能否优化。
 * @date 2019/3/23 15:43
 **/
public class Func_5_2 implements Run5.Func_5{
    @Override
    public String longestPalindrome(String s) {
        s = "aaaa";
        if (s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int maxStart = 0, maxEnd = 0, len = 0;
        for (int i = 0; i < chars.length - len/2; i++) {
            int[] ints = center(i, chars);
            if (maxEnd - maxStart  < ints[1] - ints[0] || maxStart < 0) {
                maxStart = ints[0];
                maxEnd = ints[1];
                len = maxEnd - maxStart + 1;
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

    private int[] center(int center, char[] chars) {
        int left = center, right = center;
        boolean flag = true;
        for (; left >= 0 && right < chars.length; left--, right++) {
            if (chars[left] != chars[center] && chars[right] != chars[center]) {
                flag = false;
            }
            if (chars[left] != chars[right]) {
                if (flag) {
                    // 如果第一次不一致时,其中有一个值与中心值一致，
                    // 则代表这次的回文数为偶数，相应方向的指针额外+1
                    if (chars[left] == chars[center]) {
                        if (left == 0) {
                            return new int[] {left, right - 1};
                        }
                        flag = false;
                        left --;
                    } else if (chars[right] == chars[center]) {
                        if (right == chars.length - 1) {
                            return new int[] {left + 1, right};
                        }
                        right ++;
                        flag = false;
                    }
                    if (chars[left] != chars[right]) {
                        return new int[] {left + 1, right - 1};
                    }
                } else {
                    return new int[] {left + 1, right - 1};
                }
            }
        }
        if (left < 0 && right < chars.length && flag && chars[right] == chars[center]) {
            right ++;
        }
        if (left >= 0 && right >= chars.length && flag && chars[left] == chars[center]) {
            left --;
        }
        return new int[] {left + 1, right - 1};
    }
}
