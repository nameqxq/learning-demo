package quxiqi.leetcode._23;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @see <a href="https://leetcode.com/problems/merge-k-sorted-lists//">23</>
 * @date 2019/4/10 11:46
 **/
public class Run23 {
    public interface Func_23 {
        ListNode mergeKLists(ListNode[] lists);
    }

    public static void main(String[] args) {
        Func_23 fun = new Func_23_1();
        ListNode node = fun.mergeKLists(null);
        System.out.println(node);
    }
}
