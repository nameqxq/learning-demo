package quxiqi.leetcode._6;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/zigzag-conversion/">5</>
 * @date 2019/3/23 17:46
 **/
public class Run6 {
    public interface Func_6 {
        String convert(String s, int numRows);
    }

    public static void main(String[] args) {
        Func_6 fun = new Func_6_1();
        String ss = fun.convert("ss", 1);
        System.out.println(ss);
    }
}
