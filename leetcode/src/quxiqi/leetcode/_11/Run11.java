package quxiqi.leetcode._11;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/container-with-most-water/">11</>
 * @date 2019/4/10 11:46
 **/
public class Run11 {
    public interface Func_11 {
        int maxArea(int[] height);
    }

    public static void main(String[] args) {
        Func_11 func = new Func_11_4();
        int area = func.maxArea(new int[]{1,2,3,4,5,6,7,8,9,10});
        System.out.println(area);
    }
}
