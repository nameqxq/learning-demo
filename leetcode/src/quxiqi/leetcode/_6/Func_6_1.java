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
        s = "PAYPALISHIRING";
        numRows = 4;
        int once = 2 * numRows - 1;
        StringBuilder[] t = new StringBuilder[numRows];
        for (int i = 0; i < t.length; i++) {
            t[i] = new StringBuilder();
        }

        char[] chars = s.toCharArray();
        t[0].append(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            int remainder = i % once;
            if (remainder == 0) {
                t[1].append(chars[i]);
            } else if (remainder < numRows) {
                t[remainder].append(chars[i]);
            } else {
                int sub = remainder - numRows;
                t[remainder - sub - 1].append(chars[i]);
            }
        }

        for (int i = 1; i < t.length; i++) {
            t[0].append(t[i]);
        }
        // "PINALSIGYAHRPI"
        return t[0].toString();
    }
}
