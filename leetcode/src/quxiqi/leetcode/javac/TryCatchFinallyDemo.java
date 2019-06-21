package quxiqi.leetcode.javac;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/21 10:15
 **/
public class TryCatchFinallyDemo {
    public static int foo() {
        int x = 0;
        try {
            return x;
        } finally {
            ++x;
        }
    }

    public static void main(String[] args) {
        int foo = foo();
        System.out.println(foo);
    }
}
