package quxiqi.leetcode._10;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/7/2 11:25
 **/
public class Func_10_1 implements Run10.Func_10 {

    @Override
    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }

        char[] ss = s.toCharArray();
        char[] ps = p.toCharArray();
        int[] psGroup = new int[2 * ps.length];
        int psGroupIndex = -1;
        // 解析pattern，按*分割，分组
        int index = 0;
        for (int i = 0; i < ps.length; i++) {
            char pi = ps[i];
            if (pi == '*') {
                if (i - 1 > index) {
                    psGroup[++psGroupIndex] = index;
                    psGroup[++psGroupIndex] = i - 1;
                }
                index = i + 1;
            }
        }
        if (ps.length - 1 > index) {
            psGroup[++psGroupIndex] = index;
            psGroup[++psGroupIndex] = ps.length - 1;
        }


        // 开始匹配
        int ssStart = 0;
        int startMatchedIndex = -1;
        int endMatchedIndex = -1;

        for (int i = 0; i <= psGroupIndex ; i = i + 2) {
            int start = psGroup[i];
            int end = psGroup[i+1];
            // 字符串已经匹配完成，但是还有pattern，直接返回
            if (ssStart == ss.length - 1) {
                return false;
            }
            boolean matchedBreak = false;
            for (int j = ssStart; j < ss.length; j++) {
                if (ss[j] < 'a' || ss[j] > 'z') {
                    return false;
                }
                if (ps[start] == '.' || ps[start] == ss[j]) {
                    if (startMatchedIndex == -1) {
                        startMatchedIndex = j;
                    }
                    if (start == end) {
                        // 本组全部匹配完成
                        ssStart = j + 1;
                        endMatchedIndex = j;
                        matchedBreak = true;
                        break;
                    } else {
                        start ++;
                    }
                } else {
                    // 如果不匹配，从头开始
                    start = psGroup[i];
                }
            }
            if (!matchedBreak) {
                return false;
            }
        }
        if (startMatchedIndex == -1) {
            return false;
        }
        return (startMatchedIndex == 0 || ps[0] == '*') && (endMatchedIndex == ss.length - 1 || ps[ps.length - 1] == '*');

    }
}
