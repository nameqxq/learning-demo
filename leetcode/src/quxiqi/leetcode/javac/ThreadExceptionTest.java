package quxiqi.leetcode.javac;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/7/3 17:58
 **/
public class ThreadExceptionTest {
    public static void main(String[] args) {
        setDefaultUncaughtExceptionHandler();
        test();
    }

    private static void test() {
        Thread thread = new Thread(() -> {
            System.out.println("子线程异常前");
            System.out.println(1 / 0);
            System.out.println("子线程异常后");
        });
        thread.setUncaughtExceptionHandler((t, e) ->
                System.out.println("【子线程的Handler处理异常信息】" + t.toString() + "\n" + e.getMessage()));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程异常前");
        System.out.println(1 / 0);
        System.out.println("异常后的代码不能执行了");
    }

    private static void setDefaultUncaughtExceptionHandler() {
        Thread.UncaughtExceptionHandler defaultHandler = (t, e) -> {
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.write("start------------\n");
            e.printStackTrace(printWriter);
            printWriter.write("------------end");
            printWriter.close();
            System.out.println("【默认的Handler处理异常信息】" + writer.getBuffer().toString());
        };
        Thread.setDefaultUncaughtExceptionHandler(defaultHandler);
    }
}
