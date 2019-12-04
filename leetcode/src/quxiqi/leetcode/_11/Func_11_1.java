package quxiqi.leetcode._11;

/**
 * 最朴素的思路，从左端开始往右收缩，一轮之后最右坐标-1，每次计算水的体积，并保存最大体积的水
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 12月.2019/12/3
 */
public class Func_11_1 implements Run11.Func_11 {
    @Override
    public int maxArea(int[] height) {
        int len;
        if (height == null || (len = height.length) < 2) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            int ri = len - i - 1;
            int r = height[ri];
            for (int j = 0; j < len - i; j++) {
                int h = Math.min(height[j], r);
                max = Math.max(h*(ri-j), max);
            }
        }
        return max;
    }
}
