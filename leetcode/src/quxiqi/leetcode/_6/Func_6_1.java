package quxiqi.leetcode._6;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * @date 2019/3/23 17:47
 **/
public class Func_6_1 implements Run6.Func_6{
    @Override
    public String convert(String s, int numRows) {
        // s = "A";
        // numRows = 1;
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        int once = 2 * numRows - 2;
        StringBuilder[] t = new StringBuilder[numRows];
        for (int i = 0; i < t.length; i++) {
            t[i] = new StringBuilder();
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int remainder = i % once;
            if (remainder == 0) {
                t[0].append(chars[i]);
            } else if (remainder < numRows) {
                t[remainder].append(chars[i]);
            } else {
                int sub = remainder - numRows;
                t[numRows - sub - 2].append(chars[i]);
            }
        }

        for (int i = 1; i < t.length; i++) {
            t[0].append(t[i]);
        }
        // "PINALSIGYAHRPI"
        return t[0].toString();
    }
}
