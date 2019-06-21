package quxiqi.leetcode.javac;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/20 14:31
 **/
public class LambdaTest {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("hello, lambda");
        System.out.println(r.getClass().getName());
        r.run();
    }
}
