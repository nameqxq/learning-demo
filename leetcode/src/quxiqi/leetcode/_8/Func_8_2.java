package quxiqi.leetcode._8;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/7/1 17:58
 **/
public class Func_8_2 implements Run8.Func_8 {

    @Override
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int max = Integer.MAX_VALUE / 10;
        int maxPoint = Integer.MAX_VALUE % 10;
        int result = 0;
        boolean negative = false;
        boolean begin = false;
        for (char aChar : chars) {
            if (aChar == ' ') {
                if (begin) {
                    break;
                } else {
                    continue;
                }
            }

            if (aChar >= '0' && aChar <= '9') {
                int num = ((int)aChar) - 48;
                if (result > max || (result == max && num >= (maxPoint + (negative ? 1 : 0)))) {
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + num;
                begin = true;
            } else if (aChar == '-' || aChar == '+') {
                if (!begin) {
                    negative = aChar == '-';
                    begin = true;
                } else {
                    break;
                }
            } else {
                break;
            }

        }

        return negative ? -result : result;
    }
}
