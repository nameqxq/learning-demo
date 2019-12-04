package quxiqi.leetcode._13;

import java.util.HashMap;
import java.util.Map;

/**
 * 朴素思路2,具体算法上简洁了一点，但居然还是慢
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 12月.2019/12/4
 */
public class Func_13_2 implements Run13.Func_13 {
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
        int n = 0;
        int pre = 0;
        for (char sc : s.toCharArray()) {
            int nc = map.get(sc);
            n = n + nc;
            if (nc > pre && pre != 0) {
                n = n - pre - pre;
            }
            pre = nc;
        }
        return n;
    }
}
