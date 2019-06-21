package quxiqi.leetcode.javac;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Consumer;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/20 13:58
 **/
public class MethodHandlerTest {

    public static class A {
        void print(String s) {
            System.out.println("print in A " + s);
        }
    }
    public static class B {
        void print(String s) {
            System.out.println("print in B " + s);
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        printMethodHandle(a);
        printMethodHandle(b);

        printLambda(a::print);
        printLambda(b::print);
    }

    public static void printMethodHandle(Object obj) {
        MethodType methodType = MethodType.methodType(void.class, String.class);
        try {
            MethodHandle methodHandle = MethodHandles.lookup().findVirtual(obj.getClass(), "print", methodType);
            methodHandle.invoke(obj, "yeah! -- MethodHandle");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void printLambda(Consumer<String> con) {
        con.accept("yeah! -- lambda");
    }
}
