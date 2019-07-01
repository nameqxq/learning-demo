package quxiqi.leetcode._7;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/string-to-integer-atoi/">5</>
 * @date 2019/3/23 17:46
 **/
public class Run7 {
    public interface Func_7 {
        int myAtoi(String str);
    }

    public static void main(String[] args) {
        Func_7 fun = new Func_7_1();
        int i = fun.myAtoi("-5-");
        System.out.println(i);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
