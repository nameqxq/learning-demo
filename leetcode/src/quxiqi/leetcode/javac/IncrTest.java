package quxiqi.leetcode.javac;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/20 15:51
 **/
public class IncrTest {
    public static void foo() {
        int i = 0;
        for (int j = 0; j < 50; j++)
            i = i++;
        System.out.println(i);
    }
    public static void foo2() {
        int i = 0;
        for (int j = 0; j < 50; j++)
            i = ++i;
        System.out.println(i);
    }
    public static void bar() {
        int i = 0;
        i = i++ + ++i;
        System.out.println("i=" + i);
    }
    public static void bar2() {
        int i = 0;
        i = i++ + ++i;
        System.out.println("i=" + i);
    }
    public static void main(String[] args) {

        // foo();
        // foo2();
    }

}
