package quxiqi.leetcode._23;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/4/10 12:19
 **/
public class Func_23_1 implements Run23.Func_23 {

    @Override
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(0);
        ListNode currentNode = result;
        while (true) {
            boolean breakFlag = true;
            int min = Integer.MAX_VALUE, minIndex = 0;
            for (int i = 0; i < lists.length; i++) {
                ListNode node = lists[i];
                if (node == null) {
                    continue;
                }
                if (node.val <= min) {
                    minIndex = i;
                    min = node.val;
                    breakFlag = false;
                }
            }

            if (breakFlag) {
                break;
            }

            currentNode.next = lists[minIndex];
            lists[minIndex] = lists[minIndex].next;
            currentNode.next.next = null;
            currentNode = currentNode.next;

        }

        return result.next;
    }
}
