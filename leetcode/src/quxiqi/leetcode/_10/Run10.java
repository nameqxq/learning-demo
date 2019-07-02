package quxiqi.leetcode._10;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/palindrome-number/">5</>
 * @date 2019/3/23 17:46
 **/
public class Run10 {
    public interface Func_10 {
        boolean isMatch(String s, String p);
    }

    public static void main(String[] args) {
        Func_10 fun = new Func_10_2();
        boolean bol = fun.isMatch("a", "ab*");
        System.out.println(bol);
    }
}
