package quxiqi.leetcode._12;

/**
 * 最朴素的思路 -- 结果最朴素的思路就是最快的。
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 12月.2019/12/4
 */
public class Func_12_1 implements Run12.Func_12 {
    @Override
    public String intToRoman(int num) {
        int q = num / 1000;
        int b = num / 100 - q * 10;
        int s = num / 10 - q * 100 - b * 10;
        int g = num % 10;
        StringBuilder roman = new StringBuilder();
        if (q != 0) {
            for (int i = 0; i < q; i++) {
                roman.append("M");
            }
        }
        build(b, roman, "C", "D", "M");
        build(s, roman, "X", "L", "C");
        build(g, roman, "I", "V", "X");
        return roman.toString();
    }

    private void build(int n, StringBuilder roman, String pre, String f, String t) {
        if (n != 0) {
            if (n >= 5) {
                if (n == 9) {
                    roman.append(pre).append(t);
                    n = 0;
                } else {
                    roman.append(f);
                    n = n - 5;
                }
            }
            if (n == 4) {
                roman.append(pre).append(f);
            } else {
                for (int i = 0; i < n; i++) {
                    roman.append(pre);
                }
            }
        }
    }
}
