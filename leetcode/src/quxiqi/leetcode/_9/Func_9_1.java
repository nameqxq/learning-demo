package quxiqi.leetcode._9;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/7/2 9:45
 **/
public class Func_9_1 implements Run9.Func_9 {
    @Override
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        int[] is = new int[10];
        int endIndex = -1;
        while (x > 0) {
            is[++endIndex] = x % 10;
            x = x / 10;
        }

        for (int i = 0, j=endIndex; i < j; i++, j--) {
            if (is[i] != is[j]) {
                return false;
            }
        }

        return true;
    }
}
