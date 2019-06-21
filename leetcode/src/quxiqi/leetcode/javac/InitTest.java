package quxiqi.leetcode.javac;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/31 14:59
 **/
public class InitTest {

    public static void main(String[] args) {
        Child child = new Child();
    }
}
class Super {
    static Child child = new Child();

    static {
        System.out.println("in Super static!");
    }

    public Super() {
        System.out.println("in Super init!");
    }
}

class Child extends Super{
    static {
        System.out.println("in Child static!");
    }
    public Child() {
        System.out.println("in Child init!");
    }
}