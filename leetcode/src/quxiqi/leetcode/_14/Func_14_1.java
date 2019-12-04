package quxiqi.leetcode._14;

/**
 * 假定第一个字符串为公共前缀，往后一个个过滤
 * -- 0 ms 100% and 100%
 * 公共头指针的思路就不做了，肯定比这个慢
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 12月.2019/12/4
 */
public class Func_14_1 implements Run14.Func_14 {
    @Override
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0) {
            return "";
        }
        if (len == 1) {
            return strs[0];
        }
        char[] prefix = strs[0].toCharArray();
        int ei = prefix.length - 1;
        for (int i = 1; i < len; i++) {
            if (ei < 0) {
                return "";
            }
            char[] next = strs[i].toCharArray();
            for (int j = 0; j <= ei; j++) {
                if (j >= next.length) {
                    ei = j - 1;
                    break;
                }
                if (prefix[j] != next[j]) {
                    ei = j - 1;
                    break;
                }
            }
        }
        if (ei < 0) {
            return "";
        }
        return new String(prefix, 0, ei + 1);
    }
}
