package quxiqi.leetcode._8;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/string-to-integer-atoi/">5</>
 * @date 2019/3/23 17:46
 **/
public class Run8 {
    public interface Func_8 {
        int myAtoi(String str);
    }

    public static void main(String[] args) {
        // Func_8 fun = new Func_8_1();
        Func_8 fun = new Func_8_2();
        int i = fun.myAtoi("   -5-");
        System.out.println(i);
    }
}
