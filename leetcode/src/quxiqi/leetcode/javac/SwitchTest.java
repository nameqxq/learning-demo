package quxiqi.leetcode.javac;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/20 10:39
 **/
public class SwitchTest {

    public enum Switch {
        A,B,C,;
    }

    public void switchTest(Switch s) {
        switch (s) {
            case A:
                System.out.println(s + "A");
                return;
            case B:
                System.out.println(s + "B");
                return;
            case C:
                System.out.println(s + "C");
                return;
            default:
                System.out.println("NONE!");
        }

    }
    public void switchStrTest(String s) {
        switch (s) {
            case "A":
                System.out.println(s + "A");
                return;
            case "B":
                System.out.println(s + "B");
                return;
            case "C":
                System.out.println(s + "C");
                return;
            default:
                System.out.println("NONE!");
        }

    }

    public static void main(String[] args) {
        System.out.println("A".hashCode());
    }
}
