package quxiqi.leetcode._14;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/longest-common-prefix/">14</>
 * @date 2019/12/4
 **/
public class Run14 {
    public interface Func_14 {
        String longestCommonPrefix(String[] strs);
    }

    public static void main(String[] args) {
        Func_14 func = new Func_14_1();
        String pre = func.longestCommonPrefix(new String[]{"flower","flow","flight"});
        System.out.println(pre);
    }
}
