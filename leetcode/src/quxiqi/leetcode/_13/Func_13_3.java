package quxiqi.leetcode._13;

/**
 * 朴素思路3， 嗯， 不能用map.
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 12月.2019/12/4
 */
public class Func_13_3 implements Run13.Func_13 {
    @Override
    public int romanToInt(String s) {
        int n = 0;
        int pre = 0;
        char prec = '0';
        for (char sc : s.toCharArray()) {
            int nc;
            if (sc == prec) {
                nc = pre;
            } else {
                switch (sc) {
                    case 'I': nc = 1; break;
                    case 'V': nc = 5; break;
                    case 'X': nc = 10; break;
                    case 'L': nc = 50; break;
                    case 'C': nc = 100; break;
                    case 'D': nc = 500; break;
                    default: nc = 1000;
                };
            }
            prec = sc;
            n = n + nc;
            if (nc > pre && pre != 0) {
                n = n - pre - pre;
            }
            pre = nc;
        }
        return n;
    }
}
