package quxiqi.leetcode._13;

import java.util.HashMap;
import java.util.Map;

/**
 * 朴素思路，居然比较慢
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 12月.2019/12/4
 */
public class Func_13_1 implements Run13.Func_13 {
    @Override
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>(8, 1);
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] chars = s.toCharArray();
        int len = chars.length;
        int n = 0;
        for (int i = 0; i < len; i++) {
            char sc = chars[i];
            int nc = map.get(sc);
            if (i < len - 1) {
                char scn = chars[i + 1];
                int ncn = map.get(scn);
                if (nc < ncn) {
                    n = n + ncn - nc;
                    i ++;
                } else {
                    n = n + nc;
                }
            } else {
                n = n + nc;
            }
        }
        return n;
    }
}
