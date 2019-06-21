package quxiqi.leetcode.javac;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/21 14:02
 **/
public class TryWithResource {
    public static void foo() {
        ResourceDemo resourceDemo = null;
        Exception exception = null;
        try {
            resourceDemo = new ResourceDemo();
            int i = 1/0;
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            if (resourceDemo != null) {
                if (exception != null) {
                    try {
                        resourceDemo.close();
                    } catch (Exception e) {
                        exception.addSuppressed(e);
                    }
                } else {
                    resourceDemo.close();
                }
            }
        }
    }
    public static void bar() {
        try (ResourceDemo resourceDemo = new ResourceDemo();){
            int i = 1/0;
        }
    }
    public static void main(String[] args) {
        foo();
    }

}

class ResourceDemo implements AutoCloseable {
    @Override
    public void close() {
        System.out.println("close");
        throw new RuntimeException("in close");
    }
}
