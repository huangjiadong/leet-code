package title.data_stucture;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author jad
 * @Description:
 * @date 2022/5/23 下午4:37
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);

        Solution solution = new Solution();
//        String s = "We are happy.";
//        String s1 = solution.replaceSpace(s);
//        System.out.println(s1);

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        solution.reverseList(node1);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k - 1];
        for (int i = 0; i < nums.length - k; i++) {
            int max = nums[i];
            for (int m = i; m < i + k; m++) {
                max = Math.max(max, nums[m]);
            }
            result[i] = max;
        }
        return result;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] result = new int[nums.length - k - 1];

        for (int i = 0; i < nums.length - k; i++) {
            int max = nums[i];
            for (int m = i; m < i + k; m++) {
                max = Math.max(max, nums[m]);
            }
            result[i] = max;
        }
        return result;
    }

    public String reverseLeftWords(String s, int n) {
        char[] result = new char[s.length()];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            result[s.length() - n + i] = c;
        }
        for (int i = n; i < s.length(); i++) {
            char c = s.charAt(i);
            result[i - n] = c;
        }
        return new String(result);
    }

    /**
     * 反转链表
     */
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        if (head != null) {
            newHead = new ListNode(head.val);
        } else {
            return null;
        }
        ListNode next = head.next;
        while (next != null) {
            ListNode temp = next.next;
            next.next = newHead;
            newHead = next;
            next = temp;
        }
        return newHead;
    }

    /**
     * 替换空格
     */
    public String replaceSpace(String s) {
        int len = s.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if ((' ') == (s.charAt(i))) {
                result.append("%20");
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    public int[] reversePrint(ListNode head) {
        Deque<Integer> deque = new LinkedList<>();
        while (head != null) {
            deque.push(head.val);
            head = head.next;
        }
        int[] result = new int[deque.size()];
        int i = 0;
        while (!deque.isEmpty()) {
            Integer pop = deque.pop();
            result[i++] = pop;
        }
        return result;
    }

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class MaxQueue {

        Deque<Integer> a, b;

        public MaxQueue() {
            a = new LinkedList<>();
            b = new LinkedList<>();
        }

        public int max_value() {
            if (b.isEmpty()) {
                return -1;
            }
            return b.peek();
        }

        public void push_back(int value) {
            a.offer(value);
            if (b.isEmpty()) {
                b.offer(value);
            } else {
                while (!b.isEmpty() && b.peekLast() < value) {
                    b.pollLast();
                }
                b.offer(value);
            }
        }

        public int pop_front() {
            if (a.isEmpty()) {
                return -1;
            }
            Integer resp = a.poll();
            if (resp.equals(b.peek())) {
                b.poll();
            }
            return resp;
        }
    }

    class Node {

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 栈实现队列
     */
    class CQueue {

        Deque<Integer> a, b;

        public CQueue() {
            a = new LinkedList<>();
            b = new LinkedList<>();
        }

        public void appendTail(int value) {
            a.push(value);
        }

        public int deleteHead() {
            if (b.size() != 0) {
                return b.pop();
            } else {
                if (a.size() == 0) {
                    return 0;
                } else {
                    while (a.size() != 0) {
                        b.push(a.pop());
                    }
                    return b.pop();
                }
            }
        }
    }

    class MinStack {

        LinkedList<Integer> a, b;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            a = new LinkedList<>();
            b = new LinkedList<>();
        }

        public void push(int x) {
            a.push(x);
            if (b.size() == 0 || x <= b.peek()) {
                b.push(x);
            }
        }

        public void pop() {
            if (a.size() == 0) {
                return;
            }
            int num = a.pop();
            if (b.peek() == num) {
                b.pop();
            }
        }

        public int top() {
            return a.peek();
        }

        public int min() {
            return b.peek();
        }
    }
}
