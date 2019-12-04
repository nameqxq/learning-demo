package quxiqi.leetcode._11;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 被包含的矩形的面积肯定比包含它的矩形面积小，所以我们可以假设从最高处画一根平行x轴的线，每次下降一个梯度，
 * 计算这根线的闭合区域的面积
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 12月.2019/12/3
 */
public class Func_11_3 implements Run11.Func_11 {
    @Override
    public int maxArea(int[] height) {
        int len;
        if (height == null || (len = height.length) < 2) {
            return 0;
        }
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(height[i], i);
        }
        Arrays.sort(nodes, Comparator.comparing(Node::getData).thenComparing(Node::getIndex));
        int max = 0;
        int l = nodes[len - 1].index;
        int r = l;
        for (int i = len - 1; i >= 0; i--) {
            Node node = nodes[i];
            max = Math.max(max, node.data * Math.max(node.index - l, r - node.index));
            if (node.index < l ) {
                l = node.index;
            }
            if (node.index > r) {
                r = node.index;
            }
        }
        return max;
    }
    static class Node {
        int data;
        int index;
        Node(int data, int index) {
            this.data = data;
            this.index = index;
        }

        int getData() {
            return data;
        }

        int getIndex() {
            return index;
        }
    }
}
