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
        // 两个时间段列表:
        //      可用时间列表,例: [9:00-12:00],[14:00-20:00],[16:00-21:00]
        //      不可用时间列表,例: [12:00-14:00],[20:00-22:00],[13:00-15:00]
        // 他们可能会随意自我/相互交叉,现在要找出所有可用时间段。（两个列表中都没给出的时间段视为不可用时间段）

        System.out.println(Integer.toBinaryString(-6));
        // foo();
        // foo2();
    }

}

