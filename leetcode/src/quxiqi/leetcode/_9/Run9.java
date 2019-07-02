package quxiqi.leetcode._9;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/palindrome-number/">5</>
 * @date 2019/3/23 17:46
 **/
public class Run9 {
    public interface Func_9 {
        boolean isPalindrome(int x);
    }

    public static void main(String[] args) {
        Func_9 fun = new Func_9_2();
        boolean bol = fun.isPalindrome(10010);
        System.out.println(bol);
    }
}
