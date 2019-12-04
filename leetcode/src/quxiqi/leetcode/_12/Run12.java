package quxiqi.leetcode._12;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/integer-to-roman/">12</>
 * @date 2019/12/4
 **/
public class Run12 {
    public interface Func_12 {
        String intToRoman(int num);
    }

    public static void main(String[] args) {
        Func_12 func = new Func_12_1();
        String roman = func.intToRoman(1994);
        System.out.println(roman);
    }
}
