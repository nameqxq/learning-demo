package quxiqi.leetcode._11;

/**
 * 两端往中间收缩
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 12月.2019/12/3
 */
public class Func_11_4 implements Run11.Func_11 {
    @Override
    public int maxArea(int[] height) {
        int len;
        if (height == null || (len = height.length) < 2) {
            return 0;
        }
        int max = 0;
        for (int i = 0, j = len - 1; i < j; ) {
            int lh = height[i];
            int jh = height[j];
            int h;
            if (lh < jh) {
                i ++;
                h = lh;
            } else {
                j --;
                h = jh;
            }
            max = Math.max(max, h * (j - i + 1));
        }
        return max;
    }
}
