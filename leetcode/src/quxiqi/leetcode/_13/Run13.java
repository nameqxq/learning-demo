package quxiqi.leetcode._13;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/roman-to-integer/">13</>
 * @date 2019/12/4
 **/
public class Run13 {
    public interface Func_13 {
        int romanToInt(String s);
    }

    public static void main(String[] args) {
        Func_13 func = new Func_13_2();
        int i = func.romanToInt("IV");
        System.out.println(i);
    }
}
