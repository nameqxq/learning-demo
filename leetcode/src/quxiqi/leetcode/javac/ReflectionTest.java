package quxiqi.leetcode.javac;

import java.lang.reflect.Method;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/23 12:03
 **/
public class ReflectionTest {
    private static int count = 0;
    public static void foo() {
        new Exception("foo#" + (count++)).printStackTrace();
    }
    public static void bar() {
        new Exception("bar#" + (count++)).printStackTrace();
    }
    public static void main(String[] args) throws Exception {
        Class<?> clz = Class.forName("quxiqi.leetcode.javac.ReflectionTest");
        Method method = clz.getMethod("foo");
        for (int i = 0; i < 20; i++) {
            method.invoke(null);
        }

        Method bar = clz.getMethod("bar");
        bar.invoke(null);
    }
}
