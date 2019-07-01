package quxiqi.leetcode._7;

import java.util.HashMap;
import java.util.Map;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/7/1 17:13
 **/
public class Func_7_1 implements Run7.Func_7 {
    Map<Character, Integer> map = new HashMap<>(16);
    {
        map.put('0', 0);
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);
        map.put('-', -1);
        map.put('+', -2);
    }
    @Override
    public int myAtoi(String str) {
        int max = Integer.MAX_VALUE / 10;
        int maxPoint = Integer.MAX_VALUE % 10;
        char[] chars = str.toCharArray();
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
            Integer num = map.get(aChar);
            if (num == null) {
                break;
            }
            if (num < 0 ) {
                if (!begin) {
                    negative = num == -1;
                    begin = true;
                    continue;
                } else {
                    break;
                }
            }
            begin = true;

            if (result > max || (result == max && num >= (maxPoint + (negative ? 1 : 0)))) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + num;
        }

        return negative ? -result : result;
    }
}
