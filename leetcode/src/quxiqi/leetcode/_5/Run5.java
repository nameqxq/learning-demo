package quxiqi.leetcode._5;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @see <a href="https://leetcode.com/problems/longest-palindromic-substring/">5</>
 * @date 2019/3/23 15:29
 **/
public class Run5 {
    public interface Func_5 {
        String longestPalindrome(String s);
    }
    public static void main(String[] args) {
        String s = "xasdad";
        Func_5 func = new Func_5_2();
        String s1 = func.longestPalindrome(s);
        System.out.println(s1);


    }

}
